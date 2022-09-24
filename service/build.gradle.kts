plugins {
    java
    application
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    implementation("com.google.code.gson:gson:2.9.1")
    implementation("me.func.animation-api:animation-api-protocol:2.7.27")

    implementation("com.google.guava:guava:31.1-jre")
    implementation("it.unimi.dsi:fastutil:8.5.8")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("ru.cristalix.core:microservice:1.0.12-2")
    implementation("io.netty:netty-all:4.1.78.Final")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.mockito:mockito-core:4.8.0")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

application {
    mainClass.set("me.func.ebisu.Application")
}

tasks.getByName<Jar>("jar") {
    manifest { attributes(mapOf("Main-Class" to application.mainClass)) }
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}