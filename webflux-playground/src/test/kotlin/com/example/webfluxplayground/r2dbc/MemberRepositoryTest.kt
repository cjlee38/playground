package com.example.webfluxplayground.r2dbc

import com.example.webfluxplayground.BlockHoundTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import reactor.kotlin.test.test

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberRepositoryTest(
    private val memberRepository: MemberRepository,
) : BlockHoundTest() {

    @Test
    fun test() {
        val member = Member("hello member")
        memberRepository.save(member)
            .test()
            .assertNext { assertThat(member.id).isNotNull() }
            .verifyComplete()
    }
}