package com.example.webfluxplayground.handson

import io.pivotal.literx.domain.User

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
class Part06Request {
    var repository: ReactiveRepository<User> = ReactiveUserRepository()

    //========================================================================================
    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    fun requestAllExpectFour(flux: Flux<User?>?): StepVerifier? {
        return null
    }

    //========================================================================================
    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source
    fun requestOneExpectSkylerThenRequestOneExpectJesse(flux: Flux<User?>?): StepVerifier? {
        return null
    }

    //========================================================================================
    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    fun fluxWithLog(): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Return a Flux with all users stored in the repository that prints "Starring:" at first, "firstname lastname" for all values and "The end!" on complete
    fun fluxWithDoOnPrintln(): Flux<User>? {
        return null
    }
}
