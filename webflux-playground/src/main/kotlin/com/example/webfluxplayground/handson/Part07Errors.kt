/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
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

/**
 * Learn how to deal with errors.
 *
 * @author Sebastien Deleuze
 * @see Exceptions.propagate
 */
class Part07Errors {
    //========================================================================================
    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    fun betterCallSaulForBogusMono(mono: Mono<User?>?): Mono<User>? {
        return null
    }

    //========================================================================================
    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    fun betterCallSaulAndJesseForBogusFlux(flux: Flux<User?>?): Flux<User>? {
        return null
    }

    //========================================================================================
    // TODO Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    fun capitalizeMany(flux: Flux<User?>?): Flux<User>? {
        return null
    }

    @Throws(GetOutOfHereException::class)
    fun capitalizeUser(user: User): User {
        if (user.equals(User.SAUL)) {
            throw GetOutOfHereException()
        }
        return User(user.getUsername(), user.getFirstname(), user.getLastname())
    }

    protected object GetOutOfHereException : Exception() {
        private const val serialVersionUID = 0L
    }
}
