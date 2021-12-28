plugins {
    kotlin("js")
    kotlin("plugin.serialization")
}


kotlin {
    js {
        browser {
            binaries.executable()
        }
    }
}

group = "com.praxis.kmm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":shared"))
    implementation(kotlin("stdlib-js"))
    implementation(npm("copy-webpack-plugin", "5.1.1"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.3")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.3-pre.284-kotlin-1.6.10")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.284-kotlin-1.6.10")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.284-kotlin-1.6.10")
}

afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackCli.version = "4.9.0"
    }
}