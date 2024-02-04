@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION") 
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.shushant.network"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig{
        minSdk = libs.versions.minSdkVersion.get().toInt()
    }

    buildFeatures {
        buildConfig = true
    }

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

dependencies {
    implementation(project(":core:model"))
    api(libs.mockito.core)
    testImplementation(libs.mockk)
    implementation(libs.kotlinx.coroutines.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
}

kotlin{
    jvmToolchain(17)
}

