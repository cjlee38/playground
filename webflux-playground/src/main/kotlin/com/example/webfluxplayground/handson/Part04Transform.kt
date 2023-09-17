package com.example.webfluxplayground.handson

import io.pivotal.literx.domain.User
import reactor.core.publisher.Mono

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
class Part04Transform {
    //========================================================================================
    // TODO Capitalize the user username, firstname and lastname
    fun capitalizeOne(mono: Mono<User?>?): Mono<User>? {
        return null
    }

    //========================================================================================
    // TODO Capitalize the users username, firstName and lastName
    fun capitalizeMany(flux: Flux<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    fun asyncCapitalizeMany(flux: Flux<User?>?): Flux<User>? {
        return null
    }

    fun asyncCapitalizeUser(u: User): Mono<User> {
        return Mono.just<User>(
            User(
                u.getUsername().toUpperCase(),
                u.getFirstname().toUpperCase(),
                u.getLastname().toUpperCase()
            )
        )
    }
}
