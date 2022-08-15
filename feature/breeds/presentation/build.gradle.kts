import dependencies.Libs

plugins {
    GradlePluginId.run {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(PARCELIZE)
        id(KOTLIN_KAPT)
        id(KOTLIN_Hilt)
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true

    javacOptions {
        option("-Xmaxerrs", 2000)
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

    compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
    compileOptions.targetCompatibility = JavaVersion.VERSION_1_8

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs =
            freeCompilerArgs.toMutableList().apply { add("-Xopt-in=kotlin.RequiresOptIn") }
    }
    testOptions {
        unitTests.all {
            it.jvmArgs = listOf("-XX:MaxPermSize=256m")
        }
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}
dependencies {
    Modules.Common.run {
        implementation(project(PRESENTATION))
    }
    implementation(project(Modules.Feature.Breeds.DATA))

    implementation(Libs.DependencyInjection.Hilt.core)
    kapt(Libs.DependencyInjection.Hilt.compiler)
    kapt(Libs.AndroidX.Hilt.compiler)

    //addUnitTestDependencies()
    addInstrumentTestDependencies()
}