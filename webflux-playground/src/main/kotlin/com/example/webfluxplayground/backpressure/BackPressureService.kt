package com.example.webfluxplayground.backpressure

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class BackPressureService {
    fun interval(count: Long, durationMillis: Long): Flux<List<Long>> {
        return Flux.interval(Duration.ofMillis(durationMillis))
            .take(count)
            .buffer(10)
    }

    fun rangeDelay(count: Long, durationMillis: Long): Flux<Int> {
        return Flux.range(1, count.toInt()).delayElements(Duration.ofMillis(durationMillis))
    }
}