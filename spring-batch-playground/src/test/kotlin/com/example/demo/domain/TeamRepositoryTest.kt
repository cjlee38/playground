package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TeamRepositoryTest(
    private val teamRepository: TeamRepository
) {
    @Test
    fun save() {
        val savedTeam = teamRepository.save(Team("testTeam", emptyList()))

        assertThat(savedTeam).isNotNull
        println(savedTeam.name)
    }
}