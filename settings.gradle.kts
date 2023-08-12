rootProject.name = "playground"

include("kotlin-playground")
include("spring-playground")
include("java-playground")
include("jpa-playground")
include("querydsl-playground")
include("spring-batch-playground")
include("aop-playground")
include("http-playground")
include("webflux-playground")
include("concurrency-playground")
include("kafka-streams-playground")

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.jetbrains.kotlin.jvm", "org.jetbrains.kotlin.plugin.spring",
                "org.jetbrains.kotlin.plugin.jpa", "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
            }
        }
    }
}