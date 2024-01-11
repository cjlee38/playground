package com.example.kafkastreams.streams.logging

import mu.KLogging
import org.apache.kafka.streams.processor.PunctuationType
import org.apache.kafka.streams.processor.api.Processor
import org.apache.kafka.streams.processor.api.ProcessorContext
import org.apache.kafka.streams.processor.api.Record
import org.apache.kafka.streams.state.KeyValueStore
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class LoggingProcessor: Processor<String, String, String, String> {
    companion object : KLogging()

    private lateinit var kvStore: KeyValueStore<String, String>

    override fun init(context: ProcessorContext<String, String>) {
        kvStore = context.getStateStore("loggingKeyValueStore")

        context.schedule(Duration.ofSeconds(1L), PunctuationType.STREAM_TIME) { timestamp ->
            kvStore.all().forEach {
                logger.info { "punctuator#(${it.key}, ${it.value}" }
                context.forward(Record(it.key, it.value, timestamp))
            }
        }
    }

    override fun process(record: Record<String, String>) {
        logger.info { "LoggingProcessor#process($record)" }
        kvStore.put(record.value(), record.value())
    }
}
