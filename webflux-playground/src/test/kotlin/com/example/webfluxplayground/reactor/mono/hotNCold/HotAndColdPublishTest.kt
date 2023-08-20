package com.example.webfluxplayground.reactor.mono.hotNCold

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream


class HotAndColdPublishTest {

    @Test
    fun cold() {
        val netFlux: Flux<String> = Flux.fromStream { getMovie() }
            .delayElements(Duration.ofSeconds(1))
        // you start watching the movie
        netFlux.subscribe { println("You are watching $it") }

        // I join after sometime
        Thread.sleep(2000)
        netFlux.subscribe { println("Vinsguru is watching $it") }
        Thread.sleep(10000L)
    }

    @Test
    fun hot() {
        val movieTheatre = Flux.fromStream { getMovie() }
            .delayElements(Duration.ofSeconds(1))
            .doOnEach { println(it) }
            .share()

        // you start watching the movie
        movieTheatre.subscribe { scene -> println("You are watching $scene") }

        // I join after sometime
        Thread.sleep(2000)
        movieTheatre.subscribe { scene -> println("Vinsguru is watching $scene") }
        Thread.sleep(10000L)
    }

    private fun getMovie(): Stream<String> {
        println("Got the movie streaming request")
        return Stream.of(
            "scene 1",
            "scene 2",
            "scene 3",
            "scene 4",
            "scene 5"
        )
    }
}