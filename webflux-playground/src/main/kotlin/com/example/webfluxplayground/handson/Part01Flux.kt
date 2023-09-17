package com.example.webfluxplayground.handson

import reactor.core.publisher.Flux

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see [Flux Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
 */
class Part01Flux {
    //========================================================================================
    // TODO Return an empty Flux
    fun emptyFlux(): Flux<String>? {
        return null
    }

    //========================================================================================
    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    fun fooBarFluxFromValues(): Flux<String>? {
        return null
    }

    //========================================================================================
    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    fun fooBarFluxFromList(): Flux<String>? {
        return null
    }

    //========================================================================================
    // TODO Create a Flux that emits an IllegalStateException
    fun errorFlux(): Flux<String>? {
        return null
    }

    //========================================================================================
    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    fun counter(): Flux<Long>? {
        return null
    }
}
