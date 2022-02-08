plugins {
    CommonPlugins.plugins.forEach { dependency ->
        id(dependency)
    }
    CommonPlugins.kotlinPlugins.forEach { dependency ->
        kotlin(dependency)
    }
}

version = "1.0"

kotlin {
    targets{
        android()
        iosX64()
        iosArm64()
        watchos()
        macosX64()
        macosArm64()
        watchosSimulatorArm64()
        iosSimulatorArm64() // sure all ios dependencies support this target
        jvm("desktop") {
            compilations.all {
                kotlinOptions.jvmTarget = "11"
            }
        }
        js(IR) {
            binaries.executable()
            browser {
                commonWebpackConfig {
                    cssSupport.enabled = true
                }
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                CommonMainDependencies.implementation.forEach(::implementation)
                CommonMainDependencies.api.forEach(::api)
            }
        }
        val commonTest by getting {
            dependencies {
                CommonTestDependencies.implementation.forEach(::implementation)
                CommonTestDependencies.kotlin.map { dependency ->
                    kotlin(dependency)
                }.forEach(::implementation)
            }
        }
        val androidMain by getting {
            dependencies {
                AndroidMainDependencies.implementation.forEach(::implementation)
            }
        }
        val desktopMain by getting {
            dependencies {
                ComposeDesktopDependencies.implementation.forEach(::implementation)
            }
        }

        val androidTest by getting {
            dependencies {
                AndroidTestDependencies.kotlin.map { dependency ->
                    kotlin(dependency)
                }.forEach(::implementation)
                AndroidTestDependencies.implementation.forEach(::implementation)
            }
        }
        val jsMain by getting {
            dependencies {
                WebAppCommonMainDependencies.implementation.forEach(::implementation)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val watchosSimulatorArm64Main by getting
        val watchosMain by getting

        val macosX64Main by getting {
            dependencies {
                MacOSMainDependencies.implementationx64.forEach(::implementation)
            }
        }
        val macosArm64Main by getting {
            dependencies {
                MacOSMainDependencies.implementationArm64.forEach(::implementation)
            }
        }

        val iosMain by creating {
            dependencies {
                IOSMainDependencies.implementation.forEach(::implementation)
            }
            dependsOn(commonMain)
            watchosMain.dependsOn(this)
            watchosSimulatorArm64Main.dependsOn(this)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


sqldelight {
    database("BaseIoDB") {
        packageName = "com.baseio.kmm.db"
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}
