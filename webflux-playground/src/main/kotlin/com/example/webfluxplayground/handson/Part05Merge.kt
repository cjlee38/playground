package com.example.webfluxplayground.handson

import io.pivotal.literx.domain.User
import reactor.core.publisher.Mono

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
class Part05Merge {
    //========================================================================================
    // TODO Merge flux1 and flux2 values with interleave
    fun mergeFluxWithInterleave(flux1: Flux<User?>?, flux2: Flux<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
    fun mergeFluxWithNoInterleave(flux1: Flux<User?>?, flux2: Flux<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Create a Flux containing the value of mono1 then the value of mono2
    fun createFluxFromMultipleMono(mono1: Mono<User?>?, mono2: Mono<User?>?): Flux<User>? {
        return null
    }
}
