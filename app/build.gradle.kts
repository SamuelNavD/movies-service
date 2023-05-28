plugins {
    kotlin("jvm")
    java
}



springBoot { mainClass.set("es.usj.androidapps.MoviesServiceMsApplicationKt") }

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.3.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.7")
    implementation("org.springframework.boot:spring-boot-starter-web-services:2.6.7")
    implementation("com.github.joschi.jackson:jackson-datatype-threetenbp:2.12.5")
    implementation("com.h2database:h2:2.1.212")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.7")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.6.7")
    implementation("com.smoketurner:dropwizard-swagger:2.0.12-1")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.fasterxml.jackson.jaxrs:jackson-jaxrs-xml-provider:2.13.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.2")
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
}