package com.example.webfluxplayground.operators

import org.junit.jupiter.api.Test
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.test.test

class TransformsKtTest {
    @Test
    fun `transform`() {
        (1..10).toFlux()
            .transform { it.filter { it % 2 == 0 } }
            .collectList()
            .test()
            .expectNext(listOf(2, 4, 6, 8, 10))
            .verifyComplete()
    }
}
