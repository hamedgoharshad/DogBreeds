import Modules.Feature.Breeds
import dependencies.Libs

plugins {
    GradlePluginId.run {
        id(ANDROID_APPLICATION)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
        id(KOTLIN_Hilt)
        id(SAFE_ARGS_KOTLIN)
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
        packagingOptions {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
        }

        compileSdk = COMPILE_SDK_VERSION
        buildToolsVersion = BUILD_TOOLS_VERSION

        defaultConfig {
            applicationId = APPLICATION_ID
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            versionCode = 1
            versionName = VERSION_NAME
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        }
        compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
        compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        implementation(project(DATA))
        implementation(project(PRESENTATION))
        implementation(project(TEST_SHARED))
    }
    Modules.Feature.run {
        implementation(project(Breeds.DOMAIN))
        implementation(project(Breeds.DATA))
        implementation(project(Breeds.PRESENTATION))
    }

    implementation(Libs.DependencyInjection.Hilt.core)
    kapt(Libs.DependencyInjection.Hilt.compiler)
}