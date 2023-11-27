package com.example.webfluxplayground.operators

import org.example.errorln
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.kotlin.test.testUsingVirtualTime
import java.time.Duration

class ZipsKtTest {
//    @Test
//    fun `zip with delay`() {
//        errorln("[START]")
//        val first = Mono.delay(Duration.ofSeconds(1))
//        val second = Mono.delay(Duration.ofSeconds(2))
//
//        val actual = {
//            first.zipWith(second)
//                .map { "${it.t1} + ${it.t2}".also { errorln(it) } }
//        }.testUsingVirtualTime()
//
//            .expectComplete()
//            .block()
//
//        errorln("[DONE]")
//    }

    @Test
    fun `zip with empty`() {
        errorln("[START]")
        val first = Mono.just("hello")

        val result = first.zipWhen { Mono.empty<String>() }
            .flatMap {
                errorln("first = ${it.t1}")
                errorln("second = ${it.t2}")
                Mono.just("world")
            }
            .block()
        println("result = ${result}")

        errorln("[DONE]")
    }

    @Test
    fun `zip with error`() {
        errorln("[START]")
        val first = Mono.just("hello")

        val result = first.zipWhen { Mono.error<String>(IllegalArgumentException()) }
            .flatMap {
                errorln("first = ${it.t1}")
                errorln("second = ${it.t2}")
                Mono.just("world")
            }
            .block()
        println("result = ${result}")

        errorln("[DONE]")
    }
}
