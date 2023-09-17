package com.example.webfluxplayground.operators

import io.kotest.core.spec.style.FreeSpec
import mu.KLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.test.test
import reactor.kotlin.test.testUsingVirtualTime
import java.time.Duration

class MapsKtTest : FreeSpec({
    class LazyRepository {
        fun double(value: Int, durationSeconds: Long = 1L): Mono<Int> {
            return Mono.delay(Duration.ofSeconds(durationSeconds))
                .map { value * 2 }
                .doOnNext { logger.info { "Executing $value" } }
        }

        fun doubleButSlowOnEven(value: Int): Mono<Int> {
            return Mono.just(value)
                .delayElement(if (value % 2 != 0) Duration.ZERO else Duration.ofSeconds(1))
                .map { it * 2 }
                .doOnNext { logger.info { "Executing $value" } }
        }
    }

    val lazyRepository = LazyRepository()
    val mono = Mono.just(10)
    val flux = Flux.just(1, 2, 3, 4)

    "mono" - {
        "map" {
            val doubled = mono.map { it * 2 }

            doubled.test()
                .expectNext(20)
                .verifyComplete()
        }

        "flatMap" {
            { mono.flatMap { lazyRepository.double(it, durationSeconds = 1L) } }
                .testUsingVirtualTime()
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(20)
                .verifyComplete()
        }
    }
    "flux" - {
        "map" {
            val doubled = flux.map { it * 2 }

            doubled.test()
                .expectNext(2, 4, 6, 8)
                .verifyComplete()
        }

        "flatMap" {
            { flux.flatMap { lazyRepository.doubleButSlowOnEven(it) } }
                .testUsingVirtualTime()
                .thenAwait(Duration.ofSeconds(1L))
                .expectNext(2, 6, 4, 8)
                .expectComplete()
                .verify()
        }

        "flatMapSequential" {
            { flux.flatMapSequential { lazyRepository.double(it) } }
                .testUsingVirtualTime()
                .thenAwait(Duration.ofSeconds(1L))
                .expectNext(2, 4, 6, 8)
                .verifyComplete()
        }
    }
}) {
    companion object : KLogging()
}

