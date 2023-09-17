package com.example.demo.transaction

import mu.KLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.persistence.EntityManagerFactory

@Configuration
class TaskletTransactionJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory
) {
    companion object: KLogging()

    @Bean
    fun taskletTransactionJob(): Job {
        return jobBuilderFactory.get("taskletTransactionJob")
            .start((taskletTransactionStep()))
            .build()
    }

    @Bean
    fun taskletTransactionStep(): TaskletStep {
        return stepBuilderFactory["taskletTransactionStep"]
            .tasklet { contribution, chunkContext ->
                val currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName()
                logger.info { "transaction = $currentTransactionName" }

                return@tasklet RepeatStatus.FINISHED
            }.build()
    }
}