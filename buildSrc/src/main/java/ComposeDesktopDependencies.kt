object ComposeDesktopDependencyVersions {
  const val composeDesktopWeb = "1.2.0-alpha01-dev709"

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
