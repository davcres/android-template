plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.davidcrespo.meet"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.davidcrespo.meet"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.common)
    implementation(projects.presentation.ui)

    // Core
    implementation(libs.androidx.core.ktx)

    // Koin
    implementation(libs.koin.android)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    debugImplementation(libs.androidx.ui.tooling)

    // Testing
    testImplementation(libs.junit) // Unit testing
    androidTestImplementation(libs.androidx.junit) // Instrumented testing (on device)
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose testing
    debugImplementation(libs.androidx.ui.test.manifest) // Compose testing (create a mock manifest to run tests in debug)
}
