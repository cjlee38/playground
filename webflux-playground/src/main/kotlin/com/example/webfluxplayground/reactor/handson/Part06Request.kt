package com.example.webfluxplayground.reactor.handson

import com.example.webfluxplayground.reactor.handson.domain.User
import com.example.webfluxplayground.reactor.handson.repository.ReactiveRepository
import com.example.webfluxplayground.reactor.handson.repository.ReactiveUserRepository
import reactor.core.publisher.Flux
import reactor.kotlin.test.test
import reactor.test.StepVerifier


/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
class Part06Request {
    var repository: ReactiveRepository<User> = ReactiveUserRepository()

    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    fun requestAllExpectFour(flux: Flux<User>): StepVerifier {
        return flux.test()
            .thenRequest(Long.MAX_VALUE)
            .expectNextCount(4)
            .expectComplete()
    }

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source
    fun requestOneExpectSkylerThenRequestOneExpectJesse(flux: Flux<User>): StepVerifier {
        return flux.test()
            .thenRequest(1)
            .expectNext(User.SKYLER)
            .thenRequest(1)
            .expectNext(User.JESSE)
            .thenCancel()
    }

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    fun fluxWithLog(): Flux<User> {
        return repository.findAll().log()
    }

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" at first, "firstname lastname" for all values and "The end!" on complete
    fun fluxWithDoOnPrintln(): Flux<User> {
        return repository.findAll()
            .doFirst { println("Starring:") }
            .doOnNext { println("${it.firstname} ${it.lastname}") }
            .doOnComplete { println("The end!") }
    }
}
