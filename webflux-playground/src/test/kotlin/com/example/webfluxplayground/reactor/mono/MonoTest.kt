package com.example.webfluxplayground.reactor.mono

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class MonoTest {
    private val data = "hello"

    @Test
    fun create() {
        val just = Mono.just(data)
        val from = Mono.from(just)
        val defer = Mono.defer { from }
        val empty = Mono.empty<String>()
    }

    private fun throws() {
        throw IllegalArgumentException()
    }
}