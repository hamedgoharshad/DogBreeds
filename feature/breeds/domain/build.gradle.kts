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
    }
}
dependencies {
    api(project(Modules.Common.DOMAIN))
    api(project(Modules.Common.TEST_SHARED))

    implementation(project(Modules.Feature.Bookmark.DOMAIN))

    implementation(Libs.DependencyInjection.Hilt.core)
    kapt(Libs.DependencyInjection.Hilt.compiler)
    addUnitTestDependencies()
}