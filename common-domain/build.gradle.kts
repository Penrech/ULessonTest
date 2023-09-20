plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.date.time)
                api(libs.javax.inject.kmm)

                //Coroutines
                implementation(libs.coroutines.core)

                //Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.serialization)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.dagger)
                implementation(libs.coroutines.core)
                implementation(libs.coroutines.android)
                implementation(libs.timber)
            }
        }
        val iosMain by getting

        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    namespace = "com.enrech.ulessontest.common_domain"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.get())
    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.min.sdk.get())
        targetSdk = Integer.parseInt(libs.versions.target.sdk.get())
    }
}