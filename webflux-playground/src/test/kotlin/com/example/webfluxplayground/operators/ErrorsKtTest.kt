package com.example.webfluxplayground.operators

import io.kotest.matchers.nulls.shouldBeNull
import org.example.errorln
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class ErrorsKtTest {
    @Test
    fun `operators to error`() {
        val result = Mono.just("start")
            .flatMap { Mono.error<String>(IllegalArgumentException()) }
            .map {
                errorln("map operator")
                "map"
            }
            .flatMap {
                errorln("flatMap operator")
                Mono.just("flatMap")
            }
            .subscribe { println("result") }
        Thread.sleep(1000L)
    }
}
