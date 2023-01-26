package com.study.querydslplayground.entity

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class MemberTest {

    lateinit var testEntityManager: TestEntityManager

    @Autowired
    lateinit var entityManager: EntityManager

    @BeforeEach
    fun setUp() {
        testEntityManager = TestEntityManager(entityManager.entityManagerFactory)
    }

    @Test
    fun testEntity() {
        val teamA = testEntityManager.persist(Team(name = "teamA"))
        val teamB = testEntityManager.persist(Team(name = "teamB"))

        testEntityManager.persist(Member("member1", 10, teamA))
        testEntityManager.persist(Member("member2", 20, teamA))
        testEntityManager.persist(Member("member3", 30, teamB))
        testEntityManager.persist(Member("member4", 40, teamB))

        testEntityManager.flush()
        testEntityManager.clear()

        entityManager.entityManagerFactory

        val members = entityManager.createQuery("select m from Member m", Member::class.java).resultList
        for (member in members) {
            println("member = $member")
            println("-> member.team = ${member.team}")
        }
    }
}