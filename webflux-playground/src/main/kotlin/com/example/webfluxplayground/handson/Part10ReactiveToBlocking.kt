package com.example.webfluxplayground.handson

import com.example.webfluxplayground.handson.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
class Part10ReactiveToBlocking {
    // TODO Return the user contained in that Mono
    fun monoToValue(mono: Mono<User>): User {
        return mono.block() ?: throw IllegalArgumentException()
    }

    // TODO Return the users contained in that Flux
    fun fluxToValues(flux: Flux<User>): Iterable<User> {
        return flux.toIterable()
    }
}
