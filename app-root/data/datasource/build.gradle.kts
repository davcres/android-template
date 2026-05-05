plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(projects.core.common)

    // Koin
    implementation(libs.koin.core)

    // Testing
    testImplementation(libs.junit)
}
