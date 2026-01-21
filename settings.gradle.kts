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

rootProject.name = "MultiModularApplication"
include(":app")
include(":lib")

include(":features:login")
include(":features:setting")
include(":features:signUp")

include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:datastore")
include(":core:protodatastore")
include(":core:navigator")

include(":features:home")
