plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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

    // Koin
    implementation(libs.koin.core)
}
