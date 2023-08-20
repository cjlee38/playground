package com.example.webfluxplayground

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import reactor.blockhound.BlockHound
import reactor.core.publisher.Mono
import java.time.Duration

abstract class BlockHoundTest {
    @BeforeEach
    fun setUp() {
        BlockHound.install()
    }

    @Test
    @Disabled
    fun _test() {
        BlockHound.install()

        Mono.delay(Duration.ofSeconds(1))
            .doOnNext {
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                }
            }.block()
    }
}