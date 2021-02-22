plugins {
    kotlin("jvm") version "1.4.30"
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.github.hannesbraun"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("bom"))
    implementation(kotlin("stdlib"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.github.hannesbraun.bin2lang.MainKt"
    }
}
