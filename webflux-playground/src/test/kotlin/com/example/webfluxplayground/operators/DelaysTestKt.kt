package com.example.webfluxplayground.operators

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class DelaysTestKt {
    @Test
    fun test() {
        assertThatThrownBy {
            Mono.just("hello")
                .delayUntil {
                    if (true) Mono.error<String>(IllegalArgumentException("STOP"))
                    else Mono.just("world")
                }
                .block()
        }
    }

    @Test
    fun delayElements() {
        Flux.fromIterable(listOf(1, 2, 3, 4, 5))
            .delayElements(Duration.ofMillis(1000L))
            .blockFirst()
    }
}
