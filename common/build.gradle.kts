import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
    id("org.jetbrains.compose") version Version.compose
}

version = "0.0.1"

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }
    buildFeatures {
        compose = true
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.nstnz.collector"
        sourceFolders = listOf("sqldelight")
    }
}

kotlin {
    android()
    ios()
    cocoapods {
        summary = "Multiplatform Compose Shared Test Module"
        homepage = "https://github.com/cl3m/multiplatform-compose"
        ios.deploymentTarget = iOSSdk.deploymentTarget
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "common"
            isStatic = true
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Ktor.client_core)
                implementation(Ktor.client_content_negotiation)
                implementation(Ktor.client_logging)
                implementation(Ktor.serialization_json)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation(Kodein.kodein_core)
                implementation(SqlDelight.sql_runtime)
                implementation(Prefs.prefs)
                api(precompose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.client_logging_jvm)
                implementation(Ktor.client_json_jvm)
                implementation(Ktor.client_android)
                implementation(compose.preview)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation(compose.uiTooling)
                implementation(SqlDelight.sql_android)
                implementation(Prefs.prefs)
                implementation("androidx.customview:customview:1.2.0-alpha02")
                implementation("androidx.customview:customview-poolingcontainer:1.0.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Ktor.client_ios)
                implementation(SqlDelight.sql_ios)
                implementation(Prefs.prefs)
            }
        }
    }
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}
