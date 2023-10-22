package com.example.webfluxplayground.operators

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.test.test

class MonoFluxSwapKtTest {

    @Test
    fun `flux to mono`() {
        val flux = Flux.fromIterable(listOf(1, 2, 3, 4, 5))
        val mono = flux.toMono()

        mono.test()
            .expectNext(1)
            .verifyComplete()

        val block = mono.block()
        println("block = $block")
    }

    @Test
    fun `nested flux to mono`() {
        val flux = Flux.fromIterable(listOf(listOf(1, 2, 3, 4, 5)))
        val mono = flux.toMono()

        mono.test()
            .expectNext(listOf(1, 2, 3, 4, 5))
            .verifyComplete()
    }

    @Test
    fun `flux to mono by reduce`() {
        val flux = Flux.fromIterable(listOf(1, 2, 3, 4, 5))

        val mono = flux.reduce(mutableListOf<Int>()) { mutList, v ->
            mutList.add(v)
            return@reduce mutList
        }

        mono.test()
            .expectNext(mutableListOf(1, 2, 3, 4, 5))
            .verifyComplete()
    }

    @Test
    fun `nested flux to mono by reduce`() {
        val flux = Flux.fromIterable(listOf(listOf(1, 2, 3, 4, 5)))
        val mono = flux.reduce(mutableListOf<Int>()) { mutList, list ->
            mutList.addAll(list)
            return@reduce mutList
        }

        mono.test()
            .expectNext(mutableListOf(1, 2, 3, 4, 5))
            .verifyComplete()
    }
}
