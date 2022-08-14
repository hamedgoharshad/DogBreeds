import dependencies.Libs

plugins {
    GradlePluginId.run {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
    }
}
android {
    GradleVersionConfig.run {
        compileSdk = COMPILE_SDK_VERSION
    }
}
dependencies {
    api(Libs.Kotlin.Coroutine.core)
    api(Libs.AndroidX.Paging.paging)
    api(Libs.AndroidX.Room.paging)

    /* Retrofit */
    api(Libs.Network.Retrofit.core)
    api(Libs.Network.Retrofit.moshi)
    api(Libs.Network.OkHttp.core)
    api(Libs.Network.OkHttp.logger)

    implementation (Libs.DependencyInjection.Hilt.core)
    kapt (Libs.DependencyInjection.Hilt.compiler)
}
