package com.example.kafka

import org.apache.kafka.clients.producer.KafkaProducer

fun main() {
    val configs = mapOf<String, Any>()
    val producer = KafkaProducer<String, String>(configs)
}
