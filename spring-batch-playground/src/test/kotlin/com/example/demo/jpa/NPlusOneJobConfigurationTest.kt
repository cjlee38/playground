package com.example.demo.jpa

import com.example.demo.domain.Member
import com.example.demo.domain.Team
import com.example.demo.domain.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestPropertySource

@SpringBatchTest
@SpringBootTest
@EnableBatchProcessing
@EnableAutoConfiguration
@TestPropertySource(properties = ["job.name=nPlusOneJob"])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class NPlusOneJobConfigurationTest(
    private val launcher: JobLauncherTestUtils,
    private val teamRepository: TeamRepository,
) {
    @Test
    fun test() {
        // given
        for (i in 0 until 3) {
            val members = (0..2).map { Member("batch-member-$i-$it", 25) }
            teamRepository.save(Team("batch-team-$i", members))
        }

        // when
        val jobParameters = JobParametersBuilder()
            .addLong("currentId", System.currentTimeMillis())
            .toJobParameters()
        val execution = launcher.launchJob(jobParameters)

        // then
        assertThat(execution.exitStatus).isEqualTo(ExitStatus.COMPLETED)
    }
}