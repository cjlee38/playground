package com.example.kafkastreamsplayground.init00.fakes

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun send(topic: String, payload: String) {
        kafkaTemplate.send(topic, payload)
    }
}
