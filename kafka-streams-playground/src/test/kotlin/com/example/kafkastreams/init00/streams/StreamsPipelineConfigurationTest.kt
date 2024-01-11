package com.example.kafkastreams.init00.streams

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@EmbeddedKafka(
    partitions = 3,
    brokerProperties = ["listeners=PLAINTEXT://localhost:9092"],
    ports = [9092],
    topics = ["streams-test-source", "streams-test-destination"]
)
class StreamsPipelineConfigurationTest(
    private val producer: KafkaStreamsProducer,
    private val consumer: KafkaStreamsConsumer,
) {
    @Test
    fun test() {
        producer.send("streams-test-source", "streams-test-key", "hello world !")
        Thread.sleep(1000L)
        val events = consumer.events
        println("events = $events")
    }
}
