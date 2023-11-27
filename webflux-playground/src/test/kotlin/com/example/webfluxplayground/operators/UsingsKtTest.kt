package com.example.webfluxplayground.operators

import io.kotest.core.spec.style.StringSpec
import mu.KLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

class UsingsKtTest : StringSpec({
    class MyResource {
        fun sayHello(): Flux<Int> {
            return listOf(1, 2, 3, 4).toFlux()
        }

        fun close() {
            logger.info { "closing" }
        }
    }

    "using" {
        Flux.using(
            { MyResource() }, // resourceSupplier
            { it.sayHello() }, // sourceSupplier
            { it.close() }, // resourceCleanup
            true // eager, true to clean before terminating downstream subscribers
        ).subscribe { logger.info { "onFinalSubscribe : $it" } }

        Thread.sleep(100L)
    }

    "using when 1" {
        Flux.usingWhen(
            MyResource().toMono(),  // resourceSupplier
            { it.sayHello() }, //resourceClosure
            { Mono.fromCallable { it.close() } } // asyncCleanup
        ).subscribe { logger.info { "onFinalSubscribe : $it" } }

        Thread.sleep(100L)
    }

    "using when 2" {
        Flux.usingWhen(
            MyResource().toMono(),  // resourceSupplier
            { it.sayHello() }, //resourceClosure
            { Mono.fromCallable { it.close() } }, // asyncComplete
            { resource, throwable -> Mono.fromCallable { resource.close() } }, // asyncError
            { Mono.fromCallable { it.close() } } // asyncCancel
        ).subscribe { logger.info { "onFinalSubscribe : $it" } }

        Thread.sleep(100L)
    }
}) {
    companion object : KLogging()
}
