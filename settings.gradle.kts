pluginManagement {
    repositories {
        google()
//        mavenCentral()
        jcenter()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://jitpack.io")
        google()
        jcenter()
    }
}

rootProject.name = "Wallet_App"
include(":app")
 