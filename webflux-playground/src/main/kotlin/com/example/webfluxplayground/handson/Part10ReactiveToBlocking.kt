package com.example.webfluxplayground.handson

import io.pivotal.literx.domain.User
import reactor.core.publisher.Mono

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
class Part10ReactiveToBlocking {
    //========================================================================================
    // TODO Return the user contained in that Mono
    fun monoToValue(mono: Mono<User?>?): User? {
        return null
    }

    //========================================================================================
    // TODO Return the users contained in that Flux
    fun fluxToValues(flux: Flux<User?>?): Iterable<User>? {
        return null
    }
}
