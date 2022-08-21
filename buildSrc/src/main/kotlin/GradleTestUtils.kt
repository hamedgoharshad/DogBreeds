import dependencies.Libs
import dependencies.Test
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addUnitTestDependencies() {
    "testImplementation"(Libs.AndroidX.Arch.test)
    "testImplementation"(Libs.Kotlin.Coroutine.test)
    "testImplementation"(Test.truth)
    "testImplementation"(Test.junitKtx)
    "testImplementation"(Test.jUnit)
    "testImplementation"(Test.core)
    "testImplementation"(Test.runner)
    "testImplementation"(Test.Mockk.mockk)
    "testImplementation"(Test.turbine)
    //"testImplementation"(Test.jupiter)
}

fun DependencyHandlerScope.addInstrumentTestDependencies() {
    "androidTestImplementation"(Libs.AndroidX.Arch.test)
    "androidTestImplementation"(Libs.AndroidX.Navigation.test)
    "androidTestImplementation"(Libs.AndroidX.Fragment.test)
    "androidTestImplementation"(Libs.AndroidX.Work.test)
    "androidTestImplementation"(Test.junitKtx)
    "androidTestImplementation"(Test.truth)
    "androidTestImplementation"(Test.runner)
    "androidTestImplementation"(Test.rules)
    "androidTestImplementation"(Test.Espresso.core)
    "androidTestImplementation"(Test.Mockk.androidMockk)
    //"androidTestImplementation"(Test.service)
}
