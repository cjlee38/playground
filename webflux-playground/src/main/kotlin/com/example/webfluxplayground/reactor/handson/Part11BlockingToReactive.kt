package com.example.webfluxplayground.reactor.handson

import com.example.webfluxplayground.reactor.handson.domain.User
import com.example.webfluxplayground.reactor.handson.repository.BlockingRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 *
 * For those who know RxJava:
 * - RxJava subscribeOn = Reactor subscribeOn
 * - RxJava observeOn = Reactor publishOn
 * - RxJava Schedulers.io <==> Reactor Schedulers.elastic
 *
 * @author Sebastien Deleuze
 * @see Flux.subscribeOn
 * @see Flux.publishOn
 * @see Schedulers
 */
class Part11BlockingToReactive {
    // TODO Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with a bounded elastic scheduler
    fun blockingRepositoryToFlux(repository: BlockingRepository<User>): Flux<User> {
        return Flux.defer { Flux.fromIterable(repository.findAll()) }
            .subscribeOn(Schedulers.boundedElastic())
    }

    // TODO Insert users contained in the Flux parameter in the blocking repository using a bounded elastic scheduler and return a Mono<Void> that signal the end of the operation
    fun fluxToBlockingRepository(flux: Flux<User>, repository: BlockingRepository<User>): Mono<Void> {
        return flux.publishOn(Schedulers.boundedElastic())
            .doOnNext(repository::save)
            .then()
    }
}
