plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":common-data"))
                api(project(":videoplayer-domain"))
                implementation(libs.kotlin.date.time)

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
                implementation(project(":common-data"))
                implementation(project(":videoplayer-domain"))
                implementation(libs.ktor.client.android)
                implementation(libs.timber)
                implementation(libs.dagger)
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

dependencies {
    add("kspAndroid", libs.dagger.compiler)
}

android {
    namespace = "com.enrech.ulessontest.videoplayer_data"
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