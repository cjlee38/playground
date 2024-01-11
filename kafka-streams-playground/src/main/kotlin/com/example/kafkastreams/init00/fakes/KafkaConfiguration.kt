package com.example.kafkastreams.init00.fakes

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfiguration(
    @Value("\${spring.kafka.producer.bootstrap-servers}") private val bootstrapServers: String,
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(
            mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::javaClass,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::javaClass,
            ), StringSerializer(), StringSerializer()
        )
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory)
    }
}

@Configuration
class KafkaConsumerConfiguration(
    @Value("\${spring.kafka.consumer.bootstrap-servers}") private val bootstrapServers: String,
    @Value("\${spring.kafka.consumer.auto-offset-reset}") private val autoOffsetReset: String,
    @Value("\${spring.kafka.consumer.enable-auto-commit}") private val enableAutoCommit: Boolean,
) {
    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(
            mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to autoOffsetReset,
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to enableAutoCommit
            ), StringDeserializer(), StringDeserializer()
        )
    }

    @Bean
    fun concurrentKafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, String>
    ): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>()
            .apply { this.consumerFactory = consumerFactory }
    }
}
