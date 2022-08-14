import dependencies.Libs

object GradlePluginVersion {
    private const val androidGradleVersion = "7.0.0"
    const val ANDROID_GRADLE = androidGradleVersion
    const val KOTLIN = Libs.Kotlin.version
    const val SAFE_ARGS = Libs.AndroidX.Navigation.version
    const val HILT = Libs.DependencyInjection.Hilt.version
}

object Modules {
    object Common {
        private const val root = ":common"
        const val TEST_SHARED = "$root:test-shared"
        const val PRESENTATION = "$root:presentation"
        const val DATA = "$root:data"
        const val DOMAIN = "$root:domain"
    }

    object Feature {
        private const val feature = ":feature"
        object Nearby {
            const val root = "$feature:nearby"
            const val DOMAIN = "$root:domain"
            const val DATA = "$root:data"
            const val PRESENTATION = "$root:presentation"
        }
    }
}

object GradlePluginId {
    const val PARCELIZE = "kotlin-parcelize"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val DAGGER_HILT_PLUGIN = "dagger.hilt.android.plugin"
    const val KOTLIN = "kotlin"
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val SAFE_ARGS_KOTLIN = "androidx.navigation.safeargs.kotlin"
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_Hilt = "dagger.hilt.android.plugin"
    const val FABRIC = "io.fabric"
    const val protoBufPlugin = "com.google.protobuf"
    const val protoBufVersion = "0.8.15"

}

object PackagingOptions {
    const val DEPENDENCIES = "META-INF/DEPENDENCI"
    const val LICENSE = "META-INF/LICENSE"
    const val LICENSE_TEXT = "META-INF/LICENSE.txt"
    const val LICENSE_TEXT_2 = "META-INF/license.txt"
    const val NOTICE = "META-INF/NOTICE"
    const val NOTICE_TEXT = "META-INF/NOTICE.txt"
    const val NOTICE_TEXT_2 = "META-INF/notice.txt"
    const val ASL2 = "META-INF/ASL2.0"
    const val AL2 = "META-INF/AL2.0"
    const val KOTLIN = "META-INF/*.kotlin_modul"
    const val LGPL2 = "META-INF/LGPL2.1"
}

object GradleOldWayPlugins {
    const val ANDROID_GRADLE =
        "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersion.KOTLIN}"
    const val SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
    const val HILT_GRADLE =
        "com.google.dagger:hilt-android-gradle-plugin:${GradlePluginVersion.HILT}"
}

