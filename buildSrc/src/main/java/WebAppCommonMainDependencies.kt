object WebAppDependencyVersions {
    const val kotlinxSerialization = "1.3.0"
    const val kotlinxHtmlJs = "0.7.3"
    const val wrappersKotlinStyled = "5.3.3-pre.284-kotlin-1.6.10"
    const val wrappersKotlinReact = "17.0.2-pre.284-kotlin-1.6.10"
    const val coroutines = CommonDependencyVersions.coroutines
}

object WebAppCommonMainDependencies {
    val implementation = listOf(
        "io.ktor:ktor-client-js:${CommonDependencyVersions.ktor}",
        "com.squareup.sqldelight:sqljs-driver:${CommonDependencyVersions.sqlDelight}",
    )
}

object WebAppDependencies {
    val implementation = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${WebAppDependencyVersions.coroutines}",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${WebAppDependencyVersions.kotlinxSerialization}",
        "org.jetbrains.kotlinx:kotlinx-html-js:${WebAppDependencyVersions.kotlinxHtmlJs}",
        "org.jetbrains.kotlin-wrappers:kotlin-styled:${WebAppDependencyVersions.wrappersKotlinStyled}",
        "org.jetbrains.kotlin-wrappers:kotlin-react:${WebAppDependencyVersions.wrappersKotlinReact}",
        "org.jetbrains.kotlin-wrappers:kotlin-react-dom:${WebAppDependencyVersions.wrappersKotlinReact}",
    )
    val kotlin = listOf(
        "stdlib-js"
    )
    val npm = listOf(
        "copy-webpack-plugin" to "9.0.0"
    )
}

object WebAppPlugins {
    val plugins = listOf(
        "multiplatform",
        "plugin.serialization",
    )
}
