package com.study.querydslplayground

import com.querydsl.jpa.impl.JPAQueryFactory
import com.study.querydslplayground.entity.Hello
import com.study.querydslplayground.entity.QHello
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class HelloTest() {
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun hello() {
        val hello = Hello()
        entityManager.persist(hello);

        val query = JPAQueryFactory(entityManager)
        val qHello = QHello("h")

        val result = query
            .selectFrom(qHello)
            .fetchOne()

        assertThat(result).isEqualTo(hello)
    }
}