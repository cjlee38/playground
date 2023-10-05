package com.example.webfluxplayground.handson.repository

import com.example.webfluxplayground.handson.domain.User
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class ReactiveUserRepository(
    private val delayInMs: Long = DEFAULT_DELAY_IN_MS,
    private val users: MutableList<User> = mutableListOf(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
) : ReactiveRepository<User> {
    constructor(vararg users: User) : this(DEFAULT_DELAY_IN_MS, users.toMutableList())
    constructor(delayInMs: Long, vararg users: User) : this(delayInMs, users.toMutableList())

    override fun save(userPublisher: Publisher<User>): Mono<Void> {
        return withDelay(Flux.from(userPublisher)).doOnNext(users::add).then()
    }

    override fun findFirst(): Mono<User> {
        return withDelay(Mono.just(users[0]))
    }

    override fun findAll(): Flux<User> {
        return withDelay(Flux.fromIterable(users))
    }

    override fun findById(username: String): Mono<User> {
        val user = users.firstOrNull { p: User -> p.username == username }
            ?: throw IllegalArgumentException("No user with username $username found!")
        return withDelay(Mono.just(user))
    }

    private fun withDelay(userMono: Mono<User>): Mono<User> {
        return Mono.delay(Duration.ofMillis(delayInMs))
            .flatMap { userMono }
    }

    private fun withDelay(userFlux: Flux<User>): Flux<User> {
        return Flux.interval(Duration.ofMillis(delayInMs))
            .zipWith(userFlux) { _, user -> user }
    }

    companion object {
        private const val DEFAULT_DELAY_IN_MS: Long = 100
    }
}
