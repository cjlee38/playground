plugins {
    java
}
buildscript {
    repositories {
        mavenCentral()
    }
}

/**
 * special configuration for `java-advanced-playground`
 */
subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    group = "org.example"
    version = "1.0-SNAPSHOT"

    java.sourceCompatibility = JavaVersion.VERSION_20
    java.targetCompatibility = JavaVersion.VERSION_20

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects - project(":java-advanced-playground") {
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
