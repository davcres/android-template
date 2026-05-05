plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    // Solo necesitas la API en compileOnly (tu jar no re-empaca detekt)
    compileOnly(libs.detekt.api)
}
