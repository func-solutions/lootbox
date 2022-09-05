plugins {
    java
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("org.hibernate:hibernate-core:6.1.2.Final")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("cristalix:microservice:20.11.04")
    implementation("io.netty:netty-all:4.1.78.Final")
    implementation("org.glassfish.jaxb:jaxb-runtime:3.0.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}