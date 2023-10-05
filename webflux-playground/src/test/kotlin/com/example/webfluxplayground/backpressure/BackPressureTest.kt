package com.example.webfluxplayground.backpressure

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import java.time.Duration

class BackPressureTest {

    @Test
    fun test() {
        val publisher = Flux.interval(Duration.ofMillis(100))

        publisher.buffer(Duration.ofMillis(1000))
            .subscribe { println("it = ${it}") }
        Thread.sleep(100000)
    }
}