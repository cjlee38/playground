package com.example.kafkastreams.streams.logging

import mu.KLogging
import org.apache.kafka.streams.processor.Punctuator
import org.apache.kafka.streams.state.KeyValueStore
import org.springframework.stereotype.Component

@Component
class LoggingPunctuator : Punctuator{
    lateinit var kvStore: KeyValueStore<String, String>

    companion object : KLogging()

    override fun punctuate(timestamp: Long) {
        logger.info { "LoggingPunctuator#punctuate($timestamp)" }
        for (keyValue in kvStore.all()) {
            logger.info { "LoggingPunctuator#punctuate#kvStore(${keyValue.key}, ${keyValue.value}" }
        }
    }
}
