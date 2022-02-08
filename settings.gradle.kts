pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "BaseiOKMM"
include(":androidApp")
include(":shared")
include(":watchApp")
include(":wearOS")
include(":webApp")
include(":compose-desktop")