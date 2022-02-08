import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose") version ComposeDesktopDependencyVersions.composeDesktopWeb
  application
}

group = "com.mutualmobile"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
  implementation(compose.desktop.macos_arm64)
  implementation(project(":shared"))
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}

application {
  mainClass.set("MainKt")
}