plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.appRoot.domain.models)
    implementation(projects.appRoot.domain.repository)

    // Koin
    implementation(libs.koin.core)

    // Testing
    testImplementation(libs.junit)
}
