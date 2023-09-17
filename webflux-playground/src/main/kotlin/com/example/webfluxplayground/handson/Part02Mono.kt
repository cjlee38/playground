package com.example.webfluxplayground.handson

import reactor.core.publisher.Mono

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see [Mono Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)
 */
class Part02Mono {
    //========================================================================================
    // TODO Return an empty Mono
    fun emptyMono(): Mono<String>? {
        return null
    }

    //========================================================================================
    // TODO Return a Mono that never emits any signal
    fun monoWithNoSignal(): Mono<String>? {
        return null
    }

    //========================================================================================
    // TODO Return a Mono that contains a "foo" value
    fun fooMono(): Mono<String>? {
        return null
    }

    //========================================================================================
    // TODO Create a Mono that emits an IllegalStateException
    fun errorMono(): Mono<String>? {
        return null
    }
}
