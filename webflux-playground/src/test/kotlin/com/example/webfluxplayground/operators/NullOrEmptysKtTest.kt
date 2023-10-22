package com.example.webfluxplayground.operators

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class NullOrEmptysKtTest {
    @Test
    fun test() {
        val x = "hello"
        val mono = Mono.just(x)

        mono.map { null }
            .subscribe { println(it) }
    }
}
