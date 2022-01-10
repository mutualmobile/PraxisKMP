plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
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
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("com.google.android.gms:play-services-wearable:17.1.0")

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:1.1.0-rc01")
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("androidx.compose.material:material:1.1.0-rc01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.wear:wear:1.2.0")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-rc01")
    implementation("io.ktor:ktor-client-mock:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-gson:1.6.7")
    implementation("io.insert-koin:koin-androidx-compose:3.1.4")
    androidTestImplementation("io.insert-koin:koin-test:3.1.4")
    androidTestImplementation("io.insert-koin:koin-test-junit4:3.1.4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.0-rc01")
    androidTestImplementation("com.squareup.sqldelight:sqlite-driver:1.5.3")
}
