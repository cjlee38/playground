package com.example.kafkastreamsplayground.fakes

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val objectMapper: ObjectMapper
) {
    private val _events: MutableList<String> = mutableListOf()
    val events: List<String>
        get() = _events.toList()

    @KafkaListener(topics = ["test-topic"], groupId = "test-group")
    fun consume(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("event = $payload")
        _events.add(payload)
        acknowledgment.acknowledge()
    }

    @KafkaListener(topics = ["test-topic"], groupId = "test-group")
    fun consume2(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("event = $payload")
        _events.add(payload)
        acknowledgment.acknowledge()
    }
}