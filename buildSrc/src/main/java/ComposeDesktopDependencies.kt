object ComposeDesktopDependencyVersions {
  const val composeDesktopWeb = "1.0.1-rc2"

}

object ComposeDesktopDependencies {
  val implementation = listOf(
    "com.squareup.sqldelight:sqlite-driver:${CommonDependencyVersions.sqlDelight}",
    "io.ktor:ktor-client-java:${CommonDependencyVersions.ktor}",
  )
}

object ComposeDesktopPlugins {
  val plugins = listOf(
    "org.jetbrains.compose" to ComposeDesktopDependencyVersions.composeDesktopWeb,
  )
  val kotlin = listOf("jvm" to AppDependencyVersions.kotlinGradle)
}
