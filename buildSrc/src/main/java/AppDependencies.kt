object AppDependencyVersions {
    const val kotlinGradle = "1.6.10"
    const val androidGradle = "7.1.1"
    const val kotlinxSerialization = "1.6.10"
    const val sqlDelight = "1.5.3"
    const val nativeCoroutines = "0.11.1-new-mm"
}
object AppDependencies {
    val plugins = listOf(
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${AppDependencyVersions.kotlinGradle}",
        "com.android.tools.build:gradle:${AppDependencyVersions.androidGradle}",
        "org.jetbrains.kotlin:kotlin-serialization:${AppDependencyVersions.kotlinxSerialization}",
        "com.squareup.sqldelight:gradle-plugin:${AppDependencyVersions.sqlDelight}",
        "com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:${AppDependencyVersions.nativeCoroutines}",
    )
}
