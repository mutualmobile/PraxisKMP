plugins {
    WearOSPlugins.plugins.forEach { dependency ->
        id(dependency)
    }
    WearOSPlugins.kotlinPlugins.forEach { dependency ->
        kotlin(dependency)
    }
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.baseio.wearos"
        minSdk = 30
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidDependencyVersions.composeKotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    WearOSDependencies.implementation.forEach(::implementation)
}
