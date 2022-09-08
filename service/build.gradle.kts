plugins {
    java
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    implementation("org.postgresql:postgresql:42.5.0")
    implementation("ru.cristalix.core:microservice:1.0.12-2")
    implementation("io.netty:netty-all:4.1.78.Final")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}