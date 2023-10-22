package com.example.webfluxplayground.plain

import org.junit.jupiter.api.Test
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import reactor.core.publisher.Mono

class PubSubKtTest {

    @Test
    fun test() {
        val publisher = object : Publisher<String> {
            private val data = listOf("a", "b", "c")

            override fun subscribe(s: Subscriber<in String>) {
                val subscription = object : Subscription {
                    override fun request(n: Long) {
                        runCatching { repeat(n.toInt()) { s.onNext(data[it]) } }
                            .onSuccess { s.onComplete() }
                            .onFailure { s.onError(it) }
                    }

                    override fun cancel() {}
                }
                s.onSubscribe(subscription)
            }
        }

        val subscriber = object: Subscriber<String> {
            override fun onSubscribe(s: Subscription) = s.request(3).also { println("[onSubscribe]") }
            override fun onNext(t: String) = println("[onNext] = $t")
            override fun onComplete() = println("[onComplete]")
            override fun onError(t: Throwable) = println("[onError] = $t")
        }

        publisher.subscribe(subscriber)
    }
}
