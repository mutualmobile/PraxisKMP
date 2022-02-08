import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose") version "1.0.1-rc2"
}

group = "com.mutualmobile"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
  implementation(project(":shared"))
  implementation(compose.desktop.currentOs)
}

compose.desktop {
  application {
    mainClass = "MainKt"
  }
}