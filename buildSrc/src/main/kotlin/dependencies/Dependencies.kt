package dependencies

object Libs {
    const val material = "com.google.android.material:material:1.2.1"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    object AndroidX {

        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val extensionsCore = "androidx.core:core-ktx:1.6.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val refreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        object Fragment {
            private const val fragment_version = "1.3.6"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragment_version"
            const val test = "androidx.fragment:fragment-testing:$fragment_version"
        }

        object Paging {
            private const val paging_version = "3.0.0-alpha08"
            const val paging = "androidx.paging:paging-runtime-ktx:$paging_version"
        }

        object LifeCycle {
            private const val version = "2.6.0-alpha01"
            const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${version}"
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${version}"
        }

        object Arch {
            private const val version = "2.1.0"
            const val core = "androidx.arch.core:core-common:$version"
            const val runtime = "androidx.arch.core:core-runtime:$version"
            const val test = "androidx.arch.core:core-testing:$version"
        }

        object Navigation {
            const val version = "2.5.1"
            const val core = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
        }

        object Room {
            private const val version = "2.4.3"
            const val compiler = "androidx.room:room-compiler:$version"
            const val core = "androidx.room:room-ktx:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val test = "androidx.room:room-testing:$version"
            const val paging = "androidx.room:room-paging:$version"
        }

        object DataStore {
            private const val version = "1.0.0"
            const val dataStorePreference = "androidx.datastore:datastore-preferences:$version"
            const val dataStore = "androidx.datastore:datastore:$version"
            const val dataStoreCore = "androidx.datastore:datastore-core:$version"
            const val dataStoreProtoArtifact = "com.google.protobuf:protoc:3.18.1"
            const val javaLite = "com.google.protobuf:protobuf-javalite:3.18.1"
        }

        object Hilt {
            private const val version = "1.0.0"
            const val work = "androidx.hilt:hilt-work:$version"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Work {
            private const val version = "2.7.1"
            const val runtime = "androidx.work:work-runtime-ktx:$version"
            const val test = "androidx.work:work-testing:$version"
        }
    }

    object DependencyInjection {
        object Hilt {
            const val version = "2.42"
            const val core = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-compiler:$version"
            const val testing = "com.google.dagger:hilt-android-testing:$version"
        }
    }

    object Stetho {
        private const val version = "1.5.1"
        const val okHttp = "com.facebook.stetho:stetho-okhttp3:${version}"
        const val core = "com.facebook.stetho:stetho:${version}"
    }

    object GooglePlay {
        object Location {
            private const val version = "20.0.0"
            const val core = "com.google.android.gms:play-services-location:$version"
        }
    }

    object ImageUtils {
        const val coil = "io.coil-kt:coil:1.4.0"
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"
        const val lottie = "com.airbnb.android:lottie:4.2.0"
        const val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:4.12.0"
    }

    object Kotlin {
        const val version = "1.5.31"
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutine {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Network {
        object OkHttp {
            private const val version = "4.9.2"
            const val core = "com.squareup.okhttp3:okhttp:$version"
            const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
            const val test = "com.squareup.okhttp3:mockwebserver:$version"
        }

        object Retrofit {
            private const val version = "2.9.0"
            const val core = "com.squareup.retrofit2:retrofit:$version"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
            const val moshi = "com.squareup.retrofit2:converter-moshi:$version"
        }
    }

    object Serializable {
        private const val kotlinxSerializationVersion = "1.3.0"
        const val kotlinxSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"
    }

    object Scale {
        private const val version = "1.0.6"
        const val ssp = "com.intuit.ssp:ssp-android:$version"
        const val sdp = "com.intuit.sdp:sdp-android:$version"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1.0.0"
    }

    object Compose {
        private const val composeVersion = "1.1.1"
        private const val activityComposeVersion = "1.4.0"
        private const val materialCompose = composeVersion
        private const val animVersion = composeVersion
        private const val toolingVersion = composeVersion
        private const val uiTestJunitVersion = composeVersion
        private const val lifeCycleRunTimeKtx = "2.4.1"
        private const val arrow = "1.0.1"
        private const val composeUIVersion = composeVersion
        private const val composeRuntimeVersion = composeVersion
        private const val foundationVersion = composeVersion
        private const val appcompatThemeVersion = "0.16.0"
        private const val constraintLayoutVersion = "1.0.1"
        private const val navVersion = "2.5.0"
        private const val material3Version = "1.0.0-alpha14"
        private const val hiltNavVersion = "1.0.0"
        private const val systemUIControllerVersion = "0.17.0"
        private const val coilVersion = "2.1.0"

        const val activityCompose = "androidx.activity:activity-compose:${activityComposeVersion}"
        const val material = "androidx.compose.material:material:${materialCompose}"
        const val anim = "androidx.compose.animation:animation:${animVersion}"
        const val tooling = "androidx.compose.ui:ui-tooling:${toolingVersion}"

        const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${uiTestJunitVersion}"
        const val appcompatTheme =
            "com.google.accompanist:accompanist-appcompat-theme:${appcompatThemeVersion}"
        const val ui = "androidx.compose.ui:ui:${composeUIVersion}"
        const val runtime = "androidx.compose.runtime:runtime:${composeRuntimeVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutVersion}"
        const val material3 = "androidx.compose.material3:material3:${material3Version}"
        const val material3WindowsSize =
            "androidx.compose.material3:material3-window-size-class:${material3Version}"
        const val navigation = "androidx.navigation:navigation-compose:${navVersion}"
        const val foundation = "androidx.compose.foundation:foundation:${foundationVersion}"
        const val systemUIController =
            "com.google.accompanist:accompanist-systemuicontroller:${systemUIControllerVersion}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${hiltNavVersion}"
        const val icons = "androidx.compose.material:material-icons-extended:${composeVersion}"
        const val coil = "io.coil-kt:coil-compose:${coilVersion}"
        const val testManifest = "androidx.compose.ui:ui-test-manifest:$composeRuntimeVersion"
    }

}

object Tools {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"
}

object Test {
    private const val coreVersion = "1.4.0"
    private const val robolectricVersion = "4.3.1"
    private const val junitVersion = "4.12"
    private const val jsonVersion = "20180813"
    private const val runnerVersion = "1.4.0"
    private const val serviceVersion = "1.4.1"
    private const val turbineVersion = "0.8.0"

    const val barista = "com.schibsted.spain:barista:3.9.0"
    const val json = "org.json:json:$jsonVersion"
    const val robolectricEnv = "androidx.test:core:$coreVersion"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
    const val jUnit = "junit:junit:${junitVersion}"
    const val core = "androidx.test:core-ktx:$runnerVersion"
    const val junitKtx = "androidx.test.ext:junit-ktx:1.1.1"
    const val runner = "androidx.test:runner:$runnerVersion"
    const val rules = "androidx.test:rules:$runnerVersion"
    const val truth = "com.google.truth:truth:1.1.3"
    const val service = "androidx.test.services:test-services:$serviceVersion"
    const val jupiter = "org.junit.jupiter:junit-jupiter"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    object Espresso {
        private const val version = "3.4.0"
        const val core = "androidx.test.espresso:espresso-core:$version"
        const val contrib = "androidx.test.espresso:espresso-contrib:$version"
        const val intents = "androidx.test.espresso:espresso-intents:$version"
        const val accessibility = "androidx.test.espresso:espresso-accessibility:$version"
        const val web = "androidx.test.espresso:espresso-web:$version"
        const val concurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
    }

    object Mockk {
        private const val version = "1.12.5"
        const val mockk = "io.mockk:mockk:$version"
        const val androidMockk = "io.mockk:mockk-android:$version"
    }

    object Mockito {
        private const val mockitoKotlinVersion = "2.2.0"
        private const val mockitoCoreVersion = "3.7.0"
        const val mockito = "org.mockito:mockito-core:$mockitoCoreVersion"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
    }
}
