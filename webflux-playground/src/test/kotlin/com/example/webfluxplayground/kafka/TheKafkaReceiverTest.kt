package com.example.webfluxplayground.kafka

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@EmbeddedKafka(partitions = 3, brokerProperties = ["listeners=PLAINTEXT://localhost:9092"], ports = [9092])
class TheKafkaReceiverTest(
    private val theKafkaReceiver: TheKafkaReceiver,
    private val theKafkaSender: TheKafkaSenderOutbound
) {
    @Test
    fun test() {
        theKafkaSender.send()
        Thread.sleep(1000L)
    }
}