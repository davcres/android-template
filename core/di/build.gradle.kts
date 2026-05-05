plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.davcres.template.core.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(projects.core.common)

    // App Root module
    implementation(projects.appRoot.data.datasource)
    implementation(projects.appRoot.data.repository)
    implementation(projects.appRoot.domain.usecases)
    implementation(projects.appRoot.presentation.viewmodels)
    implementation(projects.appRoot.presentation.ui)

    // Auth module
    implementation(projects.auth.data.datasource)
    implementation(projects.auth.data.repository)
    implementation(projects.auth.domain.usecases)
    implementation(projects.auth.presentation.viewmodels)
    implementation(projects.auth.presentation.ui)

    // Core
    implementation(libs.core.ktx)

    // Koin
    implementation(libs.koin.core)
}
