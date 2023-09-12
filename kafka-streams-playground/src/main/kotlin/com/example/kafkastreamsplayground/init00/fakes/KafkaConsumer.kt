package com.example.kafkastreamsplayground.init00.fakes

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
    fun consumeGroup(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("event = $payload by `consumeGroup`")
        _events.add(payload)
        acknowledgment.acknowledge()
    }

    @KafkaListener(topics = ["test-topic"], groupId = "test-group")
    fun consumeSameGroup(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("event = $payload by `consumeSameGroup`")
        _events.add(payload)
        acknowledgment.acknowledge()
    }

    @KafkaListener(topics = ["test-topic"], groupId = "test-group2")
    fun consumeDifferentGroup(@Payload payload: String, acknowledgment: Acknowledgment) {
        println("event = $payload by `consumeDifferentGroup`")
        _events.add(payload)
        acknowledgment.acknowledge()
    }
}
