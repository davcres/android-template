plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    // Koin
    implementation(libs.koin.core)

    // Navigation
    implementation(libs.navigation3.ui)
}
