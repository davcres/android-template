plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Solo necesitas la API en compileOnly (tu jar no re-empaca detekt)
    compileOnly(libs.detekt.api)
}
