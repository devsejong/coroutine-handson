import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21" apply false

    id("org.springframework.boot") version "3.0.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("org.graalvm.buildtools.native") version "0.9.18"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.0.0"
    id("org.jetbrains.kotlinx.kover") version "0.6.1"

    idea
}

group = "net.chandol.sample.study"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java-test-fixtures")
        plugin("idea")

        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jlleitschuh.gradle.ktlint-idea")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.0")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.0")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
        testImplementation("io.mockk:mockk:1.13.2")
    }

    sourceSets {
        create("integrationTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir(file("src/integrationTest/resources"))
        }
    }

    idea.module {
        testSourceDirs = testSourceDirs + project.sourceSets.getByName("integrationTest").allSource.srcDirs
        testResourceDirs = testResourceDirs + project.sourceSets.getByName("integrationTest").resources.srcDirs
    }

    tasks.withType<ProcessResources> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    val integrationTestImplementation: Configuration by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }

    task<Test>("integrationTest") {
        group = "verification"
        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
        mustRunAfter(tasks["test"])
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

koverMerged {
    enable()
}
