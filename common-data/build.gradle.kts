plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    kotlin("kapt")
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
                api(libs.kermit)
                api(libs.multiplatform.settings)

                //Coroutines
                implementation(libs.coroutines.core)

                implementation(libs.sqldelight.adapters)

                //Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.client.serialization.json)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":common-domain"))
                implementation(libs.ktor.client.android)

                implementation(libs.dagger)
                implementation(libs.androidx.java8)
                implementation(libs.androidx.process.lifecycle)

                configurations["kapt"].dependencies
                    .add(
                        org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                            "com.google.dagger",
                            "hilt-android-compiler",
                            libs.versions.dagger.get()
                        )
                    )
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }

        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    namespace = "com.enrech.ulessontest.common_data"
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