pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
  }
}

rootProject.name = "BaseiOKMM"
include(":androidApp")
include(":shared")
include(":watchApp")
include(":wearOS")
include(":webApp")
include(":compose-desktop")
include(":compose-web")