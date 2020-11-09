import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
}

group = "ru.f0x"
version = "0.0.1"
tasks.bootJar {
    archiveName = "application.jar"
    mainClassName = "ru.f0x.food.FoodApplication"
}
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("junit:junit:4.12")
    testImplementation("junit:junit:4.13")
    testImplementation("org.mockito:mockito-core:2.13.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.3.4.RELEASE")

    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("commons-io:commons-io:2.6")

    implementation("org.postgresql:postgresql:42.2.2")
    implementation("org.flywaydb:flyway-core")
    implementation("com.vladmihalcea:hibernate-types-52:2.7.1")
    implementation("commons-validator:commons-validator:1.4.1")


    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
