package com.example.webfluxplayground.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import reactor.kotlin.core.publisher.toFlux
import java.util.concurrent.atomic.AtomicInteger

@Component
class TheKafkaSenderOutbound(
    @Value("\${spring.kafka.producer.bootstrap-servers}") private val bootstrapServers: String,
) {
    @Bean("kafkaSender", destroyMethod = "close")
    fun kafkaSender(): KafkaSender<String, String> {
        return KafkaSender.create(SenderOptions.create(mapOf()))
    }

    fun send(doSend: Boolean = true, doOutbound: Boolean = true) {
        val sender = createSender()
        // sender == reactive kafka producer whose record has additional information named correlation metadata
        // outbound == reactive kafka producer but without metadata
        if (doSend) sendBySender(sender)
        if (doOutbound) sendByOutbound(sender)
    }

    private fun createSender(): KafkaSender<String, String> {
        val prop = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        )
        return KafkaSender.create(SenderOptions.create(prop))
    }

    private fun sendBySender(sender: KafkaSender<String, String>) {
        val count = AtomicInteger(0)
        val producerRecords = createRecords()
        val sendRecords = producerRecords.map { SenderRecord.create(it, count.getAndIncrement()) }

        sender.send(sendRecords)
            .doOnError { System.err.println("[sender]error = $it") }
            .doOnNext { System.err.println("[sender]success = $it") }
            .subscribe { System.err.println("[sender]subscribe = $it") }
    }

    private fun sendByOutbound(sender: KafkaSender<String, String>) {
        val outbound = sender.createOutbound()
        val records = createRecords()

        outbound.send(records)
            .then()
            .doOnError { System.err.println("[outbound]error = $it") }
            .doOnSuccess { System.err.println("[outbound]success") }
            .subscribe { System.err.println("[outbound]subscribe = $it") }
    }

}

private fun createRecords(): Flux<ProducerRecord<String, String>> {
    return (0 until 10)
        .map {
            ProducerRecord("the-topic", 0, System.currentTimeMillis(), "key", "value")
            /*
            or create sender record directly
            SenderRecord.create("topic", 0, System.currentTimeMillis(), "key", "value", it)
             */
        }
        .toFlux()
}
