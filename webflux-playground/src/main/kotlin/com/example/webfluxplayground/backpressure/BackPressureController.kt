package com.example.webfluxplayground.backpressure

import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class BackPressureController(
    private val backPressureService: BackPressureService
) {
    @GetMapping("/backpressure/interval/at-once")
    fun backpressureIntervalAtOnce(
        @RequestParam count: Long,
        @RequestParam durationMillis: Long
    ): Flux<List<Long>> {
        return backPressureService.interval(count, durationMillis)
    }

    @GetMapping("/backpressure/interval/stream",produces = [TEXT_EVENT_STREAM_VALUE])
    fun backpressureIntervalStream(
        @RequestParam count: Long,
        @RequestParam durationMillis: Long
    ): Flux<List<Long>> {
        return backPressureService.interval(count, durationMillis)
    }

    @GetMapping("/backpressure/range-dely/at-once")
    fun backpressureRangeDelayAtOnce(
        @RequestParam count: Long,
        @RequestParam durationMillis: Long
    ): Flux<Int> {
        return backPressureService.rangeDelay(count, durationMillis)
    }

    @GetMapping("/backpressure/range-dely/stream",produces = [TEXT_EVENT_STREAM_VALUE])
    fun backpressureRangeDelayStream(
        @RequestParam count: Long,
        @RequestParam durationMillis: Long
    ): Flux<Int> {
        return backPressureService.rangeDelay(count, durationMillis)
    }
}