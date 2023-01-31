package com.study.querydslplayground

import com.querydsl.core.Tuple
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.study.querydslplayground.entity.Member
import com.study.querydslplayground.entity.QMember
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
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

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

    /* 조인 - 기본 조인 */
    @Test
    fun join() {
        /* 팀 A에 소속된 모든 회원 */
        val result = jpaQueryFactory.selectFrom(member)
            .join(member.team, team) // team은 alias임에 주의하자.
            // leftJoin, RightJoin도 가능
            .where(team.name.eq("teamA"))
            .fetch()

        assertThat(result).hasSize(2)
    }

    /* 세타 조인 */
    @Test
    fun theta_join() {
        /* 회원의 이름이 팀 이름과 같은 회원 조회 */
        entityManager.persist(Member("teamA", team = null))
        entityManager.persist(Member("teamB", team = null))

        val result = jpaQueryFactory.select(member)
            .from(member, team) // from 절에 2개를 나열하는 것
            .where(member.username.eq(team.name))
            .fetch()

        assertThat(result).extracting("username")
            .containsExactly("teamA", "teamB")
        // 이대로는 outer join이 불가능하다. 그러나, on 절을 이용하면 가능하다 ->
    }

    /* 조인 - on 절 */
    @Test
    fun join_on_filtering() {
        /* 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회 */
        val tuples: List<Tuple> = jpaQueryFactory.select(member, team)
            .from(member)
            .leftJoin(member.team, team)
            .on(team.name.eq("teamA"))
            .fetch()
        println("tuples =${tuples.joinToString("\n")}")
    }

    @Test
    fun join_on_no_relation() {
        /*
        회원의 이름이 팀 이름과 같은 대상 외부 조인
         */
        entityManager.persist(Member("teamA", team = null))
        entityManager.persist(Member("teamB", team = null))
        entityManager.persist(Member("teamC", team = null))

        val result = jpaQueryFactory.select(member, team)
            .from(member)
            .leftJoin(team)
            .on(member.username.eq(team.name)) // hibernate 5.1 부터
            .fetch()

        for (tuple in result) {
            println(tuple)
        }
    }

    @PersistenceUnit
    lateinit var emf: EntityManagerFactory

    /* 조인 - 페치 조인 */
    @Test
    fun withoutFetchJoin() {
        entityManager.flush();
        entityManager.clear();

        val member = jpaQueryFactory.selectFrom(member)
            .where(member.username.eq("member1"))
            .fetchOne()

        val isLoaded = emf.persistenceUnitUtil.isLoaded(member?.team)
        assertThat(isLoaded).isFalse
    }

    @Test
    fun withFetchJoin() {
        entityManager.flush();
        entityManager.clear();

        val member = jpaQueryFactory.selectFrom(member)
            .join(member.team, team)
            .fetchJoin()
            .where(member.username.eq("member1"))
            .fetchOne()

        val isLoaded = emf.persistenceUnitUtil.isLoaded(member?.team)
        assertThat(isLoaded).isTrue
    }

    /* 서브쿼리 */
    @Test
    fun subqueryOnWhere() {
        val submember = QMember("submember")

        /* 나이가 가장 많은 회원 조회 */
        val result = jpaQueryFactory.selectFrom(member)
            .where(
                member.age.eq(
                    JPAExpressions
                        .select(submember.age.max())
                        .from(submember)
                )
            ).fetch()
        assertThat(result).hasSize(1)
    }

    @Test
    fun subqueryOnSelect() {
        val submember = QMember("submember")

        /* 나이가 평균 이상인 조회 */
        val result = jpaQueryFactory.select(member.username, JPAExpressions.select(submember.age.avg()).from(submember))
            .from(member)
            .fetch()

        assertThat(result).hasSize(2)
    }

    /* case 문 */
    @Test
    fun basicCase() {
        val result = jpaQueryFactory.select(
            member.age
                .`when`(10).then("ten")
                .`when`(20).then("twenty")
                .otherwise("other")
        ).from(member)
            .fetch()
    }

    @Test
    fun complexCase() {
        val result = jpaQueryFactory.select(
            CaseBuilder()
                .`when`(member.age.between(0, 20)).then("0~20")
                .`when`(member.age.between(21, 30)).then("21~30")
                .otherwise("other")
        ).from(member)
            .fetch()
    }

    /* 상수, 문자 더하기 */
    @Test
    fun constant() {
        val result = jpaQueryFactory.select(member.username, Expressions.constant("A"))
            .from(member)
            .fetch()
    }

    @Test
    fun concat() {
        /* {username}_{age} */
        val result = jpaQueryFactory.select(member.username.concat("_").concat(member.age.stringValue())) // StringValue는 enum 을 처리할 때 사용할 수 있다 !
            .from(member)
            .fetch()
    }
}