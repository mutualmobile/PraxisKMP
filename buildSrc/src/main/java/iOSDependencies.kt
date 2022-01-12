object IOSDependencyVersions
object IOSMainDependencies {
    val implementation = listOf(
        "com.squareup.sqldelight:native-driver:${CommonDependencyVersions.sqlDelight}",
        "io.ktor:ktor-client-ios:${CommonDependencyVersions.ktor}"
    )
}
