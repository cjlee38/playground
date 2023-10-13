package com.example.webfluxplayground.kafka

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@EmbeddedKafka(partitions = 3, brokerProperties = ["listeners=PLAINTEXT://localhost:9092"], ports = [9092])
class TheKafkaSenderTest(
    private val theKafkaSenderOutbound: TheKafkaSenderOutbound
): StringSpec({
    "test" {
        theKafkaSenderOutbound.send()
        Thread.sleep(1000L)
    }
})