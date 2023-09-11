package com.example.webfluxplayground.simultaneous

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * [ref](https://www.baeldung.com/spring-webclient-simultaneous-calls)
 */
@Service
class SimultaneousRequestService {
    private val webClient = WebClient.create("http://localhost:8081")

    fun requestUserIds(ids: List<Long>): Flux<String> {
        return Flux.fromIterable(ids)
            .flatMap { requestUserId(it) }
    }

    private fun requestUserId(id: Long): Mono<String> {
        return webClient.get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(String::class.java)
    }

    fun requestUserAndItemIds(userIds: List<Long>, itemIds: List<Long>): Flux<String> {
        return Flux.fromIterable(userIds.zip(itemIds))
            .flatMap { requestUserId(it.first).mergeWith(requestItemId(it.second)) }
    }

    private fun requestItemId(id: Long): Mono<String> {
        return webClient.get()
            .uri("/items/{id}", id)
            .retrieve()
            .bodyToMono(String::class.java)
    }
}