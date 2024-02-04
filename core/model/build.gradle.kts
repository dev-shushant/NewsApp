@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "dev.shushant.model"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}
dependencies{
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
}

