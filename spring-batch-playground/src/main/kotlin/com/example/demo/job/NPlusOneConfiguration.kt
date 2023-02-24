package com.example.demo.job

import com.example.demo.CHUNK_SIZE
import com.example.demo.domain.Member
import com.example.demo.domain.Team
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory

@Configuration
class NPlusOneConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory
) {
    @Bean
    fun nPlusOneJob(): Job {
        return jobBuilderFactory.get("nPlusOneJob")
            .start(nPlusOneStep())
            .build()
    }

    @Bean
    @JobScope
    fun nPlusOneStep(): Step {
        return stepBuilderFactory.get("nPlusOneStep")
            .chunk<Team, Member>(CHUNK_SIZE)
            .reader(nPlusOneReader())
            .processor(nPlusOneProcessr())
            .writer(nPlusOneWriter())
            .build()

    }

    @Bean
    @StepScope
    fun nPlusOneReader(): JpaPagingItemReader<out Team> {
        return JpaPagingItemReaderBuilder<Team>()
            .name("nPlusOneReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(CHUNK_SIZE)
            .queryString("select t from Team t")
            .build()
    }

    @Bean
    @StepScope
    fun nPlusOneProcessr(): ItemProcessor<Team, Member> {
        return ItemProcessor<Team, Member> {
            println("team.name : ${it.name}")
            it.members.first()
        }
    }

    @Bean
    @StepScope
    fun nPlusOneWriter(): JpaItemWriter<in Member> {
        return JpaItemWriterBuilder<Member>()
            .entityManagerFactory(entityManagerFactory)
            .build()
    }

}