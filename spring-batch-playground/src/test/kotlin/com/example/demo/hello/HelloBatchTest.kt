//package com.example.demo.hello
//
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.launch.JobLauncher
//import org.springframework.batch.core.launch.support.SimpleJobLauncher
//import org.springframework.batch.test.JobLauncherTestUtils
//import org.springframework.batch.test.JobRepositoryTestUtils
//import org.springframework.batch.test.context.SpringBatchTest
//
//@SpringBatchTest
//class HelloBatchTest(
//        val launcher: JobLauncherTestUtils
//) {
//
//    fun hello() {
//        val job: Job = applicationContext.getBean(jobName, Job::class.java)
//        val jobLauncherTestUtils = JobLauncherTestUtils()
//        jobLauncherTestUtils.jobLauncher = getJobLauncherTestUtils()
//        jobLauncherTestUtils.jobRepository = jobRepository
//        jobLauncherTestUtils.job = job
//
//        return jobLauncherTestUtils
//
//        launcher.launch
//    }
//
//    fun getJobRepositoryTestUtils() : JobRepositoryTestUtils {
//        val jobRepositoryTestUtils = JobRepositoryTestUtils()
//        jobRepositoryTestUtils.setDataSource(batchDataSource)
//        jobRepositoryTestUtils.setJobRepository(jobRepository)
//        return jobRepositoryTestUtils
//    }
//
//    fun getJobLauncherTestUtils(): JobLauncher {
//        val jobLauncher = SimpleJobLauncher()
//        jobLauncher.setJobRepository(jobRepository)
//        jobLauncher.afterPropertiesSet()
//        return jobLauncher
//    }
//}