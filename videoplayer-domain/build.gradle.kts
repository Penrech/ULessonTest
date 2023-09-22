plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.dokka")
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":common-domain"))
                api(libs.multiplatform.parcelize)
                api(libs.kotlin.date.time)
                api(libs.kotlin.serialization)

                //Coroutines
                implementation(libs.coroutines.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":common-domain"))
                implementation(libs.dagger)
                implementation(libs.coroutines.core)
                implementation(libs.coroutines.android)
            }
        }
        val iosMain by getting

        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    namespace = "com.enrech.ulessontest.videoplayer_domain"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.get())

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.min.sdk.get())
        targetSdk = Integer.parseInt(libs.versions.target.sdk.get())
    }
    buildTypes {
        debug {}
        release {}
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}