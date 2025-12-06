pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:di")
include(":core:common")
include(":core:ui")
include(":core:navigation")
include(":core:viewmodels")
include(":data:repository")
include(":data:datasource")
include(":data:models")
include(":data:source")
include(":domain:models")
include(":domain:usecases")
include(":domain:repository")
include(":presentation:ui")
include(":presentation:viewmodels")
include(":detekt-architecture-rules")
