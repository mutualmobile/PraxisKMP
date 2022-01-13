object CommonDependencyVersions {
    const val multiplatformSettings = "0.8.1"
    const val sqlDelight = "1.5.3"
    const val ktor = "1.6.7"
    const val kotlinxDateTime = "0.3.1"
    const val kotlinxSerialization = "1.3.2"
    const val coroutines = "1.6.0"
    const val koin = "3.1.4"
    const val junit = "4.13.2"
}

object CommonMainDependencies {
    val implementation = listOf(
        "com.russhwolf:multiplatform-settings:${CommonDependencyVersions.multiplatformSettings}",
        "com.squareup.sqldelight:runtime:${CommonDependencyVersions.sqlDelight}",
        "io.ktor:ktor-client-core:${CommonDependencyVersions.ktor}",
        "io.ktor:ktor-client-json:${CommonDependencyVersions.ktor}",
        "io.ktor:ktor-client-logging:${CommonDependencyVersions.ktor}",
        "io.ktor:ktor-client-serialization:${CommonDependencyVersions.ktor}",
        "org.jetbrains.kotlinx:kotlinx-datetime:${CommonDependencyVersions.kotlinxDateTime}",
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${CommonDependencyVersions.kotlinxSerialization}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CommonDependencyVersions.coroutines}",
    )

    val api = listOf(
        "io.insert-koin:koin-core:${CommonDependencyVersions.koin}"
    )
}

object CommonTestDependencies {
    val implementation = listOf(
        "com.russhwolf:multiplatform-settings-test:${CommonDependencyVersions.multiplatformSettings}",
    )
    val kotlin = listOf(
        "test-common",
        "test-annotations-common"
    )
}

object CommonPlugins {
    val plugins = listOf(
        "com.android.library",
        "kotlinx-serialization",
        "com.squareup.sqldelight",
        "com.rickclephas.kmp.nativecoroutines",
    )
    val kotlinPlugins = listOf(
        "multiplatform",
        "native.cocoapods"
    )
}
