package com.example.kafkastreams.init00.fakes

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@EmbeddedKafka(partitions = 3, brokerProperties = ["listeners=PLAINTEXT://localhost:9092"], ports = [9092])
class FakeKafkaIntegrationTest(
    private val kafkaProducer: KafkaProducer,
    private val kafkaConsumer: KafkaConsumer,
) {
    @Test
    fun test() {
        kafkaProducer.send("test-topic", "hello kafka")
        Thread.sleep(1000)
        println("kafkaConsumer = ${kafkaConsumer.events}")
    }
}
