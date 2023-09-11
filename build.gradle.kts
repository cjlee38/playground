plugins {
    java
}
buildscript {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

/**
 * special configuration for `java-advanced-playground`
 */
project(":java-advanced-playground") {
    java.sourceCompatibility = JavaVersion.VERSION_20
    java.targetCompatibility = JavaVersion.VERSION_20
}

subprojects - project(":java-advanced-playground") {
    java.sourceCompatibility = JavaVersion.VERSION_11
    java.targetCompatibility = JavaVersion.VERSION_11
}
