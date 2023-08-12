package com.example.kafkastreamsplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaStreamsPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<KafkaStreamsPlaygroundApplication>(*args)
}
