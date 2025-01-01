package com.example.kafkastreamsplayground.streams.logging

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.processor.api.ProcessorSupplier
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class LoggingStreams {
    @PostConstruct
    fun onCosntruct() {
        val builder = StreamsBuilder()
        builder.stream<String, String>("sourceTopic")
            .process( ProcessorSupplier { LoggingProcessor() })
    }
}
