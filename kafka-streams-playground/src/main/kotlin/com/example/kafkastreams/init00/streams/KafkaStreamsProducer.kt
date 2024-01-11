package com.example.kafkastreams.init00.streams

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaStreamsProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun send(topic: String, key: String, payload: String) {
        val future = kafkaTemplate.send(topic, key, payload)
    }
}
