package com.example.kafkastreams.init00.streams

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaStreamsConsumer {
    private val _events: MutableList<String> = mutableListOf()
    val events: List<String>
        get() = _events.toList()

    @KafkaListener(topics = ["streams-test-destination"], groupId = "streams-test-group")
    fun consumeStreams(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("streams consumer event = $payload")
        _events.add(payload)
        acknowledgment.acknowledge()
    }
}
