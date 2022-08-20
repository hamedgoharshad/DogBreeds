import dependencies.Libs
import Modules.Common.DOMAIN
import Modules.Common.TEST_SHARED

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
    }
}
dependencies {
    Modules.Common.run {
        api(project(DOMAIN))
        api(project(TEST_SHARED))
    }

    implementation(Libs.DependencyInjection.Hilt.core)
    kapt(Libs.DependencyInjection.Hilt.compiler)
    addUnitTestDependencies()
}