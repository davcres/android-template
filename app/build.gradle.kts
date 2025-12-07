plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotzilla)
}

android {
    namespace = "com.davcres.template"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.davcres.template"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "env"
    productFlavors {
        create("pre") {
            dimension = "env"
            applicationIdSuffix = ".pre"
            versionNameSuffix = "-pre"
            resValue("string", "app_name", "TemplateApp (Pre)")

            buildConfigField("String", "API_BASE_URL", "\"https://api-pre.example.com\"")
            buildConfigField("Boolean", "IS_PRO", "false")
        }
        create("pro") {
            dimension = "env"
            resValue("string", "app_name", "TemplateApp")

            buildConfigField("String", "API_BASE_URL", "\"https://api.example.com\"")
            buildConfigField("Boolean", "IS_PRO", "true")
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
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.appRoot.presentation.ui)
    implementation(projects.appRoot.presentation.viewmodels)

    // Core
    implementation(libs.core.ktx)

    // Koin
    implementation(libs.koin.android)

    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)

    // Jetpack Compose
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.ui.tooling)

    // Testing
    testImplementation(libs.junit) // Unit testing
    androidTestImplementation(libs.junit.test) // Instrumented testing (on device)
    androidTestImplementation(libs.ui.test.junit4) // Compose testing
    debugImplementation(libs.ui.test.manifest) // Compose testing (create a mock manifest to run tests in debug)

    // Analytics
    implementation(libs.kotzilla.sdk.compose) // Kotzilla SDK
}

kotzilla {
    composeInstrumentation = true
}
