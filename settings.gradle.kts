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

include(":detekt-architecture-rules")

include(":app")

include(":core:di")
include(":core:common")
include(":core:ui")
include(":core:navigation")
include(":core:viewmodels")

include("app-root:data:repository")
include("app-root:data:datasource")
include("app-root:data:models")
include("app-root:data:source")
include("app-root:domain:models")
include("app-root:domain:usecases")
include("app-root:domain:repository")
include("app-root:presentation:ui")
include("app-root:presentation:viewmodels")
include("app-root:presentation:models")

include("auth:data:repository")
include("auth:data:datasource")
include("auth:data:models")
include("auth:data:source")
include("auth:domain:models")
include("auth:domain:usecases")
include("auth:domain:repository")
include("auth:presentation:ui")
include("auth:presentation:viewmodels")
include("auth:presentation:models")
