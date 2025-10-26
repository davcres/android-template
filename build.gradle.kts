import com.android.tools.r8.internal.md
import io.gitlab.arturbosch.detekt.Detekt
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotzilla) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.nl.littlerobots.version.catalog.update)
}

versionCatalogUpdate {
    versionSelector(VersionSelectors.STABLE)
}

subprojects {
    // Aplica el plugin donde tengas Kotlin (Android/JVM)
    plugins.withId("org.jetbrains.kotlin.android") { apply(plugin = "io.gitlab.arturbosch.detekt") }
    plugins.withId("org.jetbrains.kotlin.jvm") { apply(plugin = "io.gitlab.arturbosch.detekt") }

    // Configuración compartida
    plugins.withId("io.gitlab.arturbosch.detekt") {
        dependencies {
            detektPlugins(project(":detekt-architecture-rules"))
            detektPlugins(libs.detekt.formatting)
        }
        detekt {
            config.setFrom(files("$rootDir/app/config/detekt/detekt.yml"))
            buildUponDefaultConfig = true
            allRules = false
            baseline = file("config/detekt/baseline.xml")
            autoCorrect = true

            /** Cheat sheet:
             * https://medium.com/codetodeploy/improve-your-kotlin-code-quality-with-detekt-in-android-135615ab8caf
             * Generate a default config -> ./gradlew detektGenerateConfig
             * Run detekt -> ./gradlew detekt
             * Generate baseline -> ./gradlew detektBaseline
             **/
        }

        tasks.withType<Detekt>().configureEach {
            reports {
                html.required.set(true)
                sarif.required.set(true)
                xml.required.set(false)
                txt.required.set(false)
                md.required.set(false)
            }
        }
    }
}

