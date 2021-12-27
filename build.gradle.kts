buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:0.11.1-new-mm")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}