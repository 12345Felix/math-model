import kotlin.collections.*

plugins {
    kotlin("jvm") version "1.5.21"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
    java
}
group = "fun.basic"
version = "1.0-SNAPSHOT"

val tornadofxVersion: String = "1.7.20"

repositories {
    mavenCentral()
}

application {
    mainClassName = "fun.display.MainKt"
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation("no.tornado:tornadofx:$tornadofxVersion")

    testImplementation(kotlin("test-junit"))
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/kotlin")
    }
}

javafx {
    version = "11.0.2"
    modules = mutableListOf("javafx.controls", "javafx.graphics")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }

}