@Suppress("DSL_SCOPE_VIOLATION") 
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.shushant.domain"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    ksp(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockk)
}
kotlin{
    jvmToolchain(17)
}

