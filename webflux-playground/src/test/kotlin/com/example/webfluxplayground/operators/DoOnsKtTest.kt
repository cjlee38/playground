package com.example.webfluxplayground.operators

import org.junit.jupiter.api.Assertions.*

class DoOnsKtTest: StringSpec({
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