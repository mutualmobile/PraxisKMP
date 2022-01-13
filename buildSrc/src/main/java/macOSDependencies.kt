object MacOSDependencyVersions
object MacOSMainDependencies {
    val implementationx64 = listOf(
        "io.ktor:ktor-client-ios:${CommonDependencyVersions.ktor}",
        "com.squareup.sqldelight:native-driver-macosx64:${CommonDependencyVersions.sqlDelight}",
    )
    val implementationArm64 = listOf(
        "io.ktor:ktor-client-ios:${CommonDependencyVersions.ktor}",
        "com.squareup.sqldelight:native-driver-macosarm64:${CommonDependencyVersions.sqlDelight}"
    )
}
