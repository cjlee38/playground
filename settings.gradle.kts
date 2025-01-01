rootProject.name = "playground"

include("kotlin-playground")
include("spring-playground")
include("java-playground")
include("jpa-playground")
include("querydsl-playground")
include("spring-batch-playground")
include("spring-cloud-playground")
include("aop-playground")
include("http-playground")
include("webflux-playground")
include("webflux-playground:remote-server")
include("concurrency-playground")
include("kafka-streams-playground")
include("java-advanced-playground")
include("netty-playground")
include("reactor-playground")
include("utils")

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

fun VersionCatalogBuilder.item(alias: String, depiction: String): String {
    val groupArtifactVersions = depiction.split(":")
    when (groupArtifactVersions.size) {
        2 -> library(alias, groupArtifactVersions[0], groupArtifactVersions[1]).withoutVersion()
        3 -> library(
            alias,
            "${groupArtifactVersions[0]}:${groupArtifactVersions[1]}:${groupArtifactVersions[2]}"
        )
    }
    return alias
}

fun VersionCatalogBuilder.spec(alias: String, vararg items: String): Spec {
    bundle(alias, items.toList())
    return Spec(alias, items.toList())
}

data class Spec(
    private val bundleAlias: String,
    private val libAliases: List<String>
) {
    val items: Array<String>
        get() = libAliases.toTypedArray()
}

/**
 * tobe handled, see [gradle documentation](https://docs.gradle.org/8.0.2/userguide/platforms.html)
 */
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // springs
            val springBootSpec = spec(
                "springboot",
                item("springboot-test", "org.springframework.boot:spring-boot-starter-test"),
                item("springboot-web", "org.springframework.boot:spring-boot-starter-web"),
                item("springboot-jpa", "org.springframework.boot:spring-boot-starter-data-jpa"),
            )
            // kotlins
            val kotlinSpec = spec(
                "kotlin",
                item("kotlin-reflect", "org.jetbrains.kotlin:kotlin-reflect"),
                item("kotlin-jackson", "com.fasterxml.jackson.module:jackson-module-kotlin"),
                item("kotlin-logging", "io.github.microutils:kotlin-logging:1.7.6"),
                item("kotest", "io.kotest:kotest-runner-junit5:5.4.2"),
            )
            // kotlins + springs
            spec(
                "kotlin-springboot",
                *springBootSpec.items,
                *kotlinSpec.items,
                item("kotest-spring", "io.kotest.extensions:kotest-extensions-spring:1.1.2")
            )
        }
    }
}
