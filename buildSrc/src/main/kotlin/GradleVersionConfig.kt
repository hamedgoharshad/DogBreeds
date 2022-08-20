object GradleVersionConfig {
    const val APPLICATION_ID = "com.hamed.task"
    const val VERSION_NAME = "1.0.0"
    const val COMPILE_SDK_VERSION = 32
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 32
    const val BUILD_TOOLS_VERSION = "32.0.0"
    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
}

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
}

object TestOptions {
    const val IS_RETURN_DEFAULT_VALUES = true
}