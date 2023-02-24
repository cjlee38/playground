package com.example.demo.hello

import com.example.demo.domain.Member
import com.querydsl.jpa.JPQLQueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory

@Configuration
@EnableBatchProcessing
class HelloBatchConfiguration(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val entityManagerFactory: EntityManagerFactory,
) {
    @Bean
    fun overdueFeeJob(): Job {
        return jobBuilderFactory.get("Hello")
            .start(helloStep())
            .build()
    }

    @Bean
    fun helloStep(): Step {
        return stepBuilderFactory.get("helloStep")
            .chunk<Hello, Hello>(100)
            .reader(helloReader())
            .processor(ItemProcessor<Hello, Hello> {
                println("name = ${it.name}")
                it
            })
            .writer(JpaItemWriterBuilder<Hello>()
                .entityManagerFactory(entityManagerFactory)
                .build())
            .build()
    }

    @Bean
    @StepScope
    fun helloReader(): ItemReader<out Hello> {
        return JpaPagingItemReaderBuilder<Hello>()
            .name("hibernateCursorItemReader")
            .queryString("select h from hello h where h.name = 'world'")
            .entityManagerFactory(entityManagerFactory)
            .build()
    }

    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }
}