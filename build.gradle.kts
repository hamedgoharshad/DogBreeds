// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven ("https://jitpack.io")
    }
    dependencies {
//        classpath(GradleOldWayPlugins.ANDROID_GRADLE)
//        classpath(GradleOldWayPlugins.KOTLIN_GRADLE)
        classpath(GradleOldWayPlugins.SAFE_ARGS)
        classpath(GradleOldWayPlugins.HILT_GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.android.tools.build:gradle:7.2.2")
    }

   /* ext {
        compose_version = '1.1.0-beta01'
    }*/
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
