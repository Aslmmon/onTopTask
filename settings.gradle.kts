pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        google()
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

rootProject.name = "onTopTasks"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":features:paginatedMasterDetail")
include(":features:upperCaseConverter")
include(":features:passwordGenerator")
include(":features:home")
include(":core:designsystem")
