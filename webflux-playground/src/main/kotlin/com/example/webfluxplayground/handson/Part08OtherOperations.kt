package com.example.webfluxplayground.handson

import com.example.webfluxplayground.handson.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
class Part08OtherOperations {

    // TODO Create a Flux of user from Flux of username, firstname and lastname.
    fun userFluxFromStringFlux(
        usernameFlux: Flux<String>,
        firstnameFlux: Flux<String>,
        lastnameFlux: Flux<String>
    ): Flux<User> {
        return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
            .map { User(it.t1, it.t2, it.t3) }
    }

    // TODO Return the mono which returns its value faster
    fun useFastestMono(mono1: Mono<User>, mono2: Mono<User>): Mono<User> {
        return Mono.firstWithValue(mono1, mono2)
    }

    // TODO Return the flux which returns the first value faster
    fun useFastestFlux(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        return Flux.firstWithValue(flux1, flux2)
    }

    // TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
    fun fluxCompletion(flux: Flux<User>): Mono<Void> {
        return flux.then()
    }

    // TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
    fun nullAwareUserToMono(user: User): Mono<User> {
        return Mono.justOrEmpty(user)
    }

    // TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
    fun emptyToSkyler(mono: Mono<User>): Mono<User> {
        return mono.defaultIfEmpty(User.SKYLER)
    }

    // TODO Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values
    fun fluxCollection(flux: Flux<User>): Mono<List<User>> {
        return flux.collectList()
    }
}
