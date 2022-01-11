plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
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
        kotlinCompilerExtensionVersion = "1.1.0-rc02"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.compose.ui:ui:1.1.0-rc01")
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("androidx.compose.material:material:1.1.0-rc01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0-rc01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-rc01")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.0-rc01")

    implementation("io.ktor:ktor-client-mock:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-gson:1.6.7")
    implementation("io.insert-koin:koin-androidx-compose:3.1.4")
    androidTestImplementation("io.insert-koin:koin-test:3.1.4")
    androidTestImplementation("io.insert-koin:koin-test-junit4:3.1.4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.0-rc01")
    androidTestImplementation("com.squareup.sqldelight:sqlite-driver:1.5.3")

    implementation("androidx.glance:glance:1.0.0-alpha01")
    implementation("androidx.glance:glance-appwidget:1.0.0-alpha01")

    implementation("androidx.compose.material3:material3:1.0.0-alpha02")

    implementation("com.google.accompanist:accompanist-insets:0.22.0-rc")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.22.0-rc")
}
