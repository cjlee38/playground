package com.example.webfluxplayground.webflux.simultaneous

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class SimultaneousRequestController(
    private val requestService: SimultaneousRequestService
) {
    @GetMapping("/users")
    fun findUsers(@RequestParam ids: List<Long>): Flux<String> {
        return requestService.requestUserIds(ids)
    }

    @GetMapping("/usersAndItems")
    fun findUsersAndItems(@RequestParam ids: List<Long>): Flux<String> {
        return requestService.requestUserAndItemIds(ids, ids)
    }
}
