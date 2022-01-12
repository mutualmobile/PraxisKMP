object WearOSDependencyVersions {
    const val core = "1.7.0"
    const val playServicesWearable = "17.1.0"
    const val percentLayout = "1.0.0"
    const val legacySupport = "1.0.0"
    const val recyclerView = "1.2.1"
    const val wear = "1.2.0"
    const val material = AndroidDependencyVersions.material
    const val compose = AndroidDependencyVersions.compose
    const val coil = AndroidDependencyVersions.coil
    const val lifecycleRuntime = AndroidDependencyVersions.lifecycleRuntime
    const val composeActivity = AndroidDependencyVersions.composeActivity
}

object WearOSDependencies {
    val implementation = listOf(
        "androidx.core:core-ktx:${WearOSDependencyVersions.core}",
        "com.google.android.gms:play-services-wearable:${WearOSDependencyVersions.playServicesWearable}",
        "com.google.android.material:material:${WearOSDependencyVersions.material}",
        "androidx.compose.ui:ui:${WearOSDependencyVersions.compose}",
        "io.coil-kt:coil-compose:${WearOSDependencyVersions.coil}",
        "androidx.compose.material:material:${WearOSDependencyVersions.compose}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${WearOSDependencyVersions.lifecycleRuntime}",
        "androidx.activity:activity-compose:${WearOSDependencyVersions.composeActivity}",
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
