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
import java.util.function.Supplier

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see [StepVerifier Javadoc](https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html)
 */
class Part03StepVerifier {
    //========================================================================================
    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    fun expectFooBarComplete(flux: Flux<String?>?) {
        fail()
    }

    //========================================================================================
    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    fun expectFooBarError(flux: Flux<String?>?) {
        fail()
    }

    //========================================================================================
    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    fun expectSkylerJesseComplete(flux: Flux<User?>?) {
        fail()
    }

    //========================================================================================
    // TODO Expect 10 elements then complete and notice how long the test takes.
    fun expect10Elements(flux: Flux<Long?>?) {
        fail()
    }

    //========================================================================================
    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    fun expect3600Elements(supplier: Supplier<Flux<Long?>?>?) {
        fail()
    }

    private fun fail() {
        throw AssertionError("workshop not implemented")
    }
}
