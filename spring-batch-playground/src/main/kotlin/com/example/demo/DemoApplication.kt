package com.example.demo

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableBatchProcessing
@EnableJpaRepositories
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
