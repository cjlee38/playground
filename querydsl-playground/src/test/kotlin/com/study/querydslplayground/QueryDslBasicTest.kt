package com.study.querydslplayground

import com.querydsl.core.Tuple
import com.querydsl.jpa.impl.JPAQueryFactory
import com.study.querydslplayground.entity.Member
import com.study.querydslplayground.entity.QMember.member
import com.study.querydslplayground.entity.QTeam.team
import com.study.querydslplayground.entity.Team
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import javax.persistence.EntityManager

@DataJpaTest
class QueryDslBasicTest {

    @Autowired
    lateinit var entityManager: EntityManager
    lateinit var testEntityManager: TestEntityManager
    lateinit var jpaQueryFactory: JPAQueryFactory

    @BeforeEach
    fun setUp() {
        testEntityManager = TestEntityManager(entityManager.entityManagerFactory)
        jpaQueryFactory = JPAQueryFactory(entityManager)

        val teamA = testEntityManager.persist(Team(name = "teamA"))
        val teamB = testEntityManager.persist(Team(name = "teamB"))

        testEntityManager.persist(Member("member1", 10, teamA))
        testEntityManager.persist(Member("member2", 20, teamA))
        testEntityManager.persist(Member("member3", 30, teamB))
        testEntityManager.persist(Member("member4", 40, teamB))

        entityManager.flush()
        entityManager.clear()
    }

    /* 시작 */
    @Test
    fun startJPQL() {
        // find member1
        val username = "member1"
        val findMember = entityManager.createQuery(
            "select m from Member m where m.username = :username", Member::class.java
        )
            .setParameter("username", username)
            .singleResult

        assertThat(findMember.username).isEqualTo(username)
    }

    @Test
    fun startQueryDsl() {
        val username = "member1"
        val findMember = jpaQueryFactory.select(member)
            .from(member)
            .where(member.username.eq(username))
            .fetchOne()

        assertThat(findMember?.username).isEqualTo(username)
    }

    /* 결과 조회 */
    @Test
    fun search() {

        val username = "member1"
        val findMember = jpaQueryFactory.selectFrom(member)
            .where(
                member.username.eq(username)
                    .and(member.age.eq(10))
            ) // and 조건을 추가해줄 수 있다.
            .where(
                member.username.eq(username),
                member.age.eq(10)
            ) // vararg를 사용할 수도 있다.
            .where(null) // null 을 넣어도 사용할 수 있다.
            .fetchOne() // null or entity. 2개 이상이면 예외가 발생한다.

        assertThat(findMember?.username).isEqualTo(username)
    }

    @Test
    fun resultFetch() {
        // total count를 가져오기 위해 count 쿼리를 날리고, 실제 쿼리를 날리는 두 번의 작업이 들어간다.
        val members = jpaQueryFactory.selectFrom(member)
            .fetchResults()
        val count = jpaQueryFactory.selectFrom(member)
            .fetchCount()
    }

    /* 정렬 */
    @Test
    fun sort() {
        val memberA = testEntityManager.persist(Member("", 100, null))
        val memberB = testEntityManager.persist(Member("member5", 100, null))
        val memberC = testEntityManager.persist(Member("member6", 100, null))

        val members = jpaQueryFactory.selectFrom(member)
            .where(member.age.eq(100))
            .orderBy(member.age.desc(), member.username.asc().nullsLast())
            .fetch()

        assertThat(members).containsExactly(memberA, memberB, memberC)
    }

    /* 페이징 */
    @Test
    fun paging_1() {
        val members = jpaQueryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1) // offset -> 앞에 몇 개를 스킵할것인가. 1이면 하나를 스킵한다는 것
            .limit(2)
            .fetch()

        assertThat(members.size).isEqualTo(2)
    }

    @Test
    fun paging_2() {
        val members = jpaQueryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1) // offset -> 앞에 몇 개를 스킵할것인가. 1이면 하나를 스킵한다는 것
            .limit(2)
            .fetchResults()

        assertThat(members.total).isEqualTo(4)
        assertThat(members.limit).isEqualTo(2)
        assertThat(members.offset).isEqualTo(1)
        assertThat(members.results).hasSize(2)
    }

    /* 집합 */
    @Test
    fun aggregation() {
        val tuples: List<Tuple> = jpaQueryFactory.select(
            member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min()
        ).from(member)
            .fetch()

        println(tuples[0])
    }

    @Test
    fun group() {
        /* 팀의 이름과 각 팀의 평균 연령을 구한다 */
        val tuples = jpaQueryFactory.select(team.name, member.age.avg())
            .from(member)
            .join(member.team, team)
            .groupBy(team.name)
            .fetch()

        val teamA = tuples[0]

        assertThat(teamA.get(team.name)).isEqualTo("teamA")
        assertThat(teamA.get(member.age.avg())?.toInt()).isEqualTo(15)



        val teamB = tuples[1]
        assertThat(teamB.get(team.name)).isEqualTo("teamB")
        assertThat(teamB.get(member.age.avg())?.toInt()).isEqualTo(35)
    }
}