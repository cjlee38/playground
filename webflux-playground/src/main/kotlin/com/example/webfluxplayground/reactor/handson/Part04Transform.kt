package com.example.webfluxplayground.reactor.handson

import com.example.webfluxplayground.reactor.handson.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.Locale

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
class Part04Transform {
    // TODO Capitalize the user username, firstname and lastname
    fun capitalizeOne(mono: Mono<User>): Mono<User> {
        return mono.map { asyncCapitalizeUser(it).block() }
    }

    // TODO Capitalize the users username, firstName and lastName
    fun capitalizeMany(flux: Flux<User>): Flux<User> {
        return flux.map { asyncCapitalizeUser(it).block() }
    }

    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    fun asyncCapitalizeMany(flux: Flux<User>): Flux<User> {
        return flux.flatMap { asyncCapitalizeUser(it) }
    }

    fun asyncCapitalizeUser(u: User): Mono<User> {
        return Mono.just(
            User(
                u.username.uppercase(Locale.getDefault()),
                u.firstname.uppercase(Locale.getDefault()),
                u.lastname.uppercase(Locale.getDefault())
            )
        )
    }
}
