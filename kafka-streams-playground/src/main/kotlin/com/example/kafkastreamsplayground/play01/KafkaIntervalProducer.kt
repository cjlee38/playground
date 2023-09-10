package com.example.kafkastreamsplayground.play01

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class KafkaIntervalProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private var count = 0

    @Scheduled(cron = "* * * * * *")
    fun producerPerSecond() {
        kafkaTemplate.send("topic", "produce : [$count]")
    }
}
