/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.webfluxplayground.handson

import io.pivotal.literx.domain.User
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

/**
 * Learn how to adapt from/to RxJava 3 Observable/Single/Flowable and Java 8+ CompletableFuture.
 *
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * Reactive Streams compliant + there are [Mono.from] and [Flux.from]
 * factory methods.
 *
 * For RxJava 3, you should not use Reactor Adapter but only RxJava 3 and Reactor Core.
 *
 * @author Sebastien Deleuze
 */
class Part09Adapt {
    //========================================================================================
    // TODO Adapt Flux to RxJava Flowable
    fun fromFluxToFlowable(flux: Flux<User?>?): Flowable<User>? {
        return null
    }

    // TODO Adapt RxJava Flowable to Flux
    fun fromFlowableToFlux(flowable: Flowable<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Adapt Flux to RxJava Observable
    fun fromFluxToObservable(flux: Flux<User?>?): Observable<User>? {
        return null
    }

    // TODO Adapt RxJava Observable to Flux
    fun fromObservableToFlux(observable: Observable<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Adapt Mono to RxJava Single
    fun fromMonoToSingle(mono: Mono<User?>?): Single<User>? {
        return null
    }

    // TODO Adapt RxJava Single to Mono
    fun fromSingleToMono(single: Single<User?>?): Mono<User>? {
        return null
    }

    //========================================================================================
    // TODO Adapt Mono to Java 8+ CompletableFuture
    fun fromMonoToCompletableFuture(mono: Mono<User?>?): CompletableFuture<User>? {
        return null
    }

    // TODO Adapt Java 8+ CompletableFuture to Mono
    fun fromCompletableFutureToMono(future: CompletableFuture<User?>?): Mono<User>? {
        return null
    }
}
