package com.example.kafkastreams.init00.streams

import mu.KLogging
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class StreamsPipelineConfiguration(
    @Value("\${spring.kafka.producer.bootstrap-servers}") private val bootstrapServers: String,
    @Value("\${spring.kafka.streams.application-id}") private val applicationId: String,
) {
    companion object : KLogging()

    @Bean
    fun testStreams(): KafkaStreams {
        val streamsProperties = Properties()
        streamsProperties[StreamsConfig.APPLICATION_ID_CONFIG] = applicationId
        streamsProperties[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        streamsProperties[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        streamsProperties[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass

        val source = "streams-test-source"
        val destination = "streams-test-destination"

        val streamsBuilder = StreamsBuilder()

        streamsBuilder.stream<String, String>(source)
            .peek { key, value -> println("streams received a message : $key=$value") }
            .to(destination)

        val topology = streamsBuilder.build()
        return KafkaStreams(topology, streamsProperties).apply { start() }
    }
}
