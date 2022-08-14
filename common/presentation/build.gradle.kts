import dependencies.Libs
import dependencies.Libs.AndroidX.Fragment
import dependencies.Libs.AndroidX.LifeCycle
import dependencies.Libs.AndroidX.LifeCycle.lifecycleCompose
import dependencies.Libs.AndroidX.Navigation

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
        id(DAGGER_HILT_PLUGIN)
        id(SAFE_ARGS_KOTLIN)
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
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Modules.Common.DATA))
    implementation(Libs.Kotlin.kotlin_stdlib)
    api(Libs.Scale.sdp)
    api(Libs.Scale.ssp)
    api(Libs.material)
    Libs.AndroidX.run {
        api(appCompat)
        api(extensionsCore)
        api(constraintLayout)
        api(refreshLayout)
        api(Fragment.fragmentKtx)
        api(LifeCycle.liveData)
        api(LifeCycle.commonJava8)
        api(LifeCycle.runtime)
        api(LifeCycle.viewModel)
        api(Navigation.core)
        api(Navigation.ui)
    }
    Libs.Compose.run {
        api(activityCompose)
        api(material)
        api(anim)
        api(tooling)
        api(uiTestJunit)
        api(navigation)
        api(icons)
        api(coil)
    }
    LifeCycle.run {
        api(lifecycleCompose)
        api(lifecycleRuntime)
    }
    api(Libs.ImageUtils.coil)

    implementation(Libs.DependencyInjection.Hilt.core)
    kapt(Libs.DependencyInjection.Hilt.compiler)

    addUnitTestDependencies()
}