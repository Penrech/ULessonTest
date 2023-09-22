plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.dokka")
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.date.time)

                //Coroutines
                implementation(libs.coroutines.core)

                implementation(libs.sqldelight.adapters)
                implementation(libs.sqldelight.coroutines)

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

                api(libs.sqldelight.android.driver)
            }
        }
        val iosMain by getting {
            dependencies {
                api(libs.sqldelight.native.driver)
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

sqldelight {
    databases {
        create("ULessonCacheDb") {
            packageName.set("com.enrech.ulessontest.common_domain")
        }
    }
}

android {
    namespace = "com.enrech.ulessontest.common_domain"
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