package com.example.webfluxplayground.operators

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

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
}
