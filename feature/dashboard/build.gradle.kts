@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.shushant.dashboard"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        testInstrumentationRunner = "dev.shushant.test.HiltTestRunner"
    }

    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(libs.coil.kt.compose)
    kspAndroidTest(libs.hilt.android.testing)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockk)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.material3)
    implementation(libs.compose.foundation)
    ksp(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.mock.android)
    androidTestImplementation(libs.mock.agent)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

}

kotlin {
    jvmToolchain(17)
}

