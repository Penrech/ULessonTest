pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ULessonTest"
include(":app")
include(":main")
include(":common")
include(":chapter")
include(":videoplayer")
include(":home")
include(":common-resources")
