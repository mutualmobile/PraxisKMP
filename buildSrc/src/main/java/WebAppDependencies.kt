object WebAppDependencyVersions
object WebAppDependencies {
    val implementation = listOf(
        "io.ktor:ktor-client-js:${CommonDependencyVersions.ktor}",
        "com.squareup.sqldelight:sqljs-driver:${CommonDependencyVersions.sqlDelight}",
    )
}
