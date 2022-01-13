plugins {
    AndroidPluginDependencies.plugins.forEach { (lib, v) ->
        if (v.isNotBlank()) {
            id(lib) version v
        } else {
            id(lib)
        }
    }
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.baseio.kmm.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    AndroidDependencies.implementation.forEach(::implementation)
    AndroidDependencies.androidTestImplementation.forEach(::androidTestImplementation)
    AndroidDependencies.debugImplementation.forEach(::debugImplementation)
}
