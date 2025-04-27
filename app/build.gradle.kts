plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.agricare"
    compileSdk = 35  // Updated to the latest stable SDK version

    defaultConfig {
        applicationId = "com.example.agricare"
        minSdk = 24
        targetSdk = 33  // Updated to the latest stable SDK version
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.coil.compose)
    debugImplementation(libs.ui.tooling)

    // Material 3 (optional)
    implementation(libs.material3)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)

    // Core KTX
    implementation(libs.androidx.core.ktx.v1160)

    // Material Icons Extended
    implementation(libs.androidx.material.icons.extended)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v121)
    androidTestImplementation(libs.androidx.espresso.core.v361)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    // Kotlin Annotation Processing (if needed)
    // kapt(libs.dagger.compiler)  // Uncomment if using Dagger or any annotation processing
}
