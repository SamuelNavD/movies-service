plugins {
    kotlin("jvm")
    java
}



springBoot { mainClass.set("es.usj.androidapps.MoviesServiceMsApplication") }

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-web-services:2.6.4")
    implementation("com.github.joschi.jackson:jackson-datatype-threetenbp:2.6.4")
    implementation("com.h2database:h2:2.1.210")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.4")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.6.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
}