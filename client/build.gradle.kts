dependencies {
    compileOnly("cristalix:bukkit-core:21.01.30")
    compileOnly("cristalix:dark-paper:21.02.03")
    compileOnly("me.func:animation-api:2.7.4")

    implementation("dev.xdark:feder:1.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "ebisu-client"
            from(components["java"])
        }
    }
}
