package com.example.webfluxplayground.handson.repository

import com.example.webfluxplayground.handson.domain.User
import reactor.core.publisher.Mono

class BlockingUserRepository(
    private val reactiveRepository: ReactiveRepository<User> = ReactiveUserRepository()
) : BlockingRepository<User> {
    var callCount = 0
        private set

    constructor(delayInMs: Long) : this(ReactiveUserRepository(delayInMs))
    constructor(vararg users: User) : this(ReactiveUserRepository(*users))
    constructor(delayInMs: Long, vararg users: User) : this(ReactiveUserRepository(delayInMs, *users))

    override fun save(user: User) {
        callCount++
        reactiveRepository.save(Mono.just(user)).block()
    }

    override fun findFirst(): User? {
        callCount++
        return reactiveRepository.findFirst().block()
    }

    override fun findAll(): Iterable<User> {
        callCount++
        return reactiveRepository.findAll().toIterable()
    }

    override fun findById(username: String): User? {
        callCount++
        return reactiveRepository.findById(username).block()
    }
}
