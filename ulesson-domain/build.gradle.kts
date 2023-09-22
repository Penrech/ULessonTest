plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.dokka")
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
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
                api(project(":common-domain"))
            }
        }
        val iosMain by getting

        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    namespace = "com.enrech.ulessontest.ulesson_domain"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.get())
    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.min.sdk.get())
        targetSdk = Integer.parseInt(libs.versions.target.sdk.get())
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}