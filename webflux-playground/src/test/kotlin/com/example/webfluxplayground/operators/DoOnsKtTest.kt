package com.example.webfluxplayground.operators

import io.kotest.core.spec.style.StringSpec
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class DoOnsKtTest : StringSpec({
    "compare `doOn...` & `on...`" {
        Flux.just(1, 2, 3)
            .doOnError { println("similar with `onEach`, just do whatever.") }
            .onErrorResume { Mono.empty() }
    }

    "doOn" {
        "error" {

        }

        "doOn"
    }

    "on" {

    }
})
