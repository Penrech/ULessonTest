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
include(":common-domain")
include(":common-data")
include(":ulesson-data")
include(":ulesson-domain")
include(":videoplayer-domain")
include(":videoplayer-data")
