import dependencies.Libs
import dependencies.Test

plugins {
    GradlePluginId.run {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
    }
}
android {
    GradleVersionConfig.run {
        compileSdk = COMPILE_SDK_VERSION
        buildToolsVersion = BUILD_TOOLS_VERSION
        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    buildFeatures {
        dataBinding= true
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(Modules.Common.DATA))
    implementation(Libs.Kotlin.kotlin_stdlib)
    implementation(Libs.Kotlin.Coroutine.test)
    implementation(Test.junitKtx)
    implementation(Libs.AndroidX.Fragment.test)
    implementation(Libs.AndroidX.LifeCycle.liveData)
    implementation(Test.Espresso.core)
    implementation(Test.Mockk.mockk)
    implementation(Test.runner)
    implementation(Libs.AndroidX.Room.core)
    implementation(Libs.AndroidX.Room.runtime)
}