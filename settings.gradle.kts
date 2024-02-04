@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

include(":feature:dashboard")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "NewsApp"
include(":app")
include(":core:network")
include(":core:domain")
include(":core:model")
include(":core:designsystem")
include(":core:data")