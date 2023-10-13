package com.example.webfluxplayground.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions


@Configuration
class TheKafkaReceiver(
    @Value("\${spring.kafka.producer.bootstrap-servers}") private val bootstrapServers: String,
) {
    @Bean
    fun theTopicKafkaReceiver(): KafkaReceiver<Int, String>? {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "sample-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        )

        val receiverOptions = ReceiverOptions.create<Int, String>(props).subscription(listOf("the-topic"))
        return KafkaReceiver.create(receiverOptions)
            .also { receiver ->
                receiver.receive().subscribe {
                    System.err.println("[receiver]result = $it")
                    it.receiverOffset().acknowledge()
                }
            }
    }
}