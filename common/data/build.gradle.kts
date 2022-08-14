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

android {
    GradleVersionConfig.run {
        compileSdk = COMPILE_SDK_VERSION
        buildToolsVersion = BUILD_TOOLS_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER

            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/api/\"")
            buildConfigField("String", "DATABASE_NAME", "\"breeds\"")
            /*buildConfigField(
                "String",
                "API_KEY",
                "\"fsq3J/XvPRpYbNjXkyjOUP32t3w+W/xjL5ytiRA17vvB9Fc=\""
            )*/
        }
        compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
        compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    api(project(Modules.Common.DOMAIN))
    api(Libs.timber)
    api (Libs.DependencyInjection.Hilt.core)
    api(Libs.Kotlin.Coroutine.android)
    api(Libs.AndroidX.Room.core)
    api(Libs.AndroidX.Room.runtime)
    kapt(Libs.AndroidX.Room.compiler)
    kapt(Libs.AndroidX.Hilt.compiler)
    /* DataStore */
    api(Libs.AndroidX.DataStore.dataStorePreference)
    api(Libs.AndroidX.DataStore.dataStoreCore)

    api(Libs.AndroidX.Work.runtime)
    api(Libs.AndroidX.Hilt.work)
    kapt (Libs.DependencyInjection.Hilt.compiler)
    addUnitTestDependencies()
}