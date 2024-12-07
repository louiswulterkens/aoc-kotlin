plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.24.2") {
        because("Todd Ginsberg: I prefer AssertJ's fluid assertions over JUnit or Hamcrest")
    }
}

tasks.test {
    useJUnitPlatform()
}