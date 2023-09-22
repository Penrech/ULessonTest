plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

kotlin {
    androidTarget()
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
                implementation(libs.sqldelight.coroutines)

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
    namespace = "com.enrech.ulessontest.common_data"
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