object WearOSDependencyVersions {
    const val core = "1.7.0"
    const val playServicesWearable = "17.1.0"
    const val percentLayout = "1.0.0"
    const val legacySupport = "1.0.0"
    const val recyclerView = "1.2.1"
    const val wear = "1.2.0"
}

object WearOSDependencies {
    val implementation = listOf(
        "androidx.core:core-ktx:${WearOSDependencyVersions.core}",
        "com.google.android.gms:play-services-wearable:${WearOSDependencyVersions.playServicesWearable}",
        "com.google.android.material:material:${AndroidDependencyVersions.material}",
        "androidx.compose.ui:ui:${AndroidDependencyVersions.compose}",
        "io.coil-kt:coil-compose:${AndroidDependencyVersions.coil}",
        "androidx.compose.material:material:${AndroidDependencyVersions.compose}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${AndroidDependencyVersions.lifecycleRuntime}",
        "androidx.activity:activity-compose:${AndroidDependencyVersions.composeActivity}",
        "androidx.percentlayout:percentlayout:${WearOSDependencyVersions.percentLayout}",
        "androidx.legacy:legacy-support-v4:${WearOSDependencyVersions.legacySupport}",
        "androidx.recyclerview:recyclerview:${WearOSDependencyVersions.recyclerView}",
        "androidx.wear:wear:${WearOSDependencyVersions.wear}",
    )
}
object WearOSPlugins {
    val plugins = listOf(
        "com.android.application",
        "kotlin-android",
    )
    val kotlinPlugins = listOf(
        "android"
    )
}
