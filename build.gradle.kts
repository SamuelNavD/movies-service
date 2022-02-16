import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("org.sonarqube") version "3.3"
    id("application")
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10" apply false
    id("org.openapi.generator") version "5.3.0"
    application
    java
}

group = "es.usj.androidapps"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<BootJar> {
        enabled = false
    }
}

noArg {
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Entity")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("org.springframework.stereotype.Component")
    annotation("org.springframework.stereotype.Repository")
    annotation("org.springframework.stereotype.Service")
    annotation("org.springframework.context.annotation.Configuration")
    annotation("org.springframework.context.annotation.Bean")
}

subprojects {

    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("idea")
        plugin("kotlin")
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
        implementation("com.mchange:c3p0:0.9.5.5")
        implementation("org.hibernate:hibernate-core")
        implementation("org.springframework.boot:spring-boot-starter-security:2.5.6")
        implementation("org.springframework.security:spring-security-test:5.5.1")
        implementation("org.springframework.data:spring-data-jpa:2.5.6")
        implementation("com.squareup.okhttp3:okhttp:4.9.3")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
        implementation("org.web3j:core:4.8.9")
        //OpenApiGenerator
        implementation("org.openapitools:jackson-databind-nullable:0.2.1")

        testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6")
        testImplementation(platform("org.junit:junit-bom:5.7.2"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
        testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6")
        testImplementation("org.testcontainers:postgresql:1.16.2")
        testImplementation("org.testcontainers:junit-jupiter:1.16.2")
    }
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}

tasks.withType<KotlinCompile> {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xemit-jvm-type-annotations")
        jvmTarget = "11"
    }
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("file.encoding", "UTF-8")
    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
    }
}

val service = "movies"

val kotlin = Action<org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension> {
    inputSpec.set("$rootDir/docs/$service-api.yaml")
    outputDir.set("${project.projectDir}/$service-client")
    apiPackage.set("$group.$service.api")
    invokerPackage.set("$group.$service.invoker")
    modelPackage.set("$group.$service.model")
    generatorName.set("java")
    configOptions.apply {
        mapOf(
            "hideGenerationTimestamp" to "true",
            "library" to "jvm-retrofit2",
            "serializationLibrary" to "gson",
            "dateLibrary" to "java8",
            "packageName" to "${group}.$service.api",
            "enumPropertyNaming" to "original",
            "collectionType" to "list",
            "useCoroutines" to true,
            "groupId" to "${project.group}.$service",
            "artifactId" to "$service-client"
        )
    }
}

openApiGenerate(kotlin)
