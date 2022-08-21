package com.hamed.common.testshared

import com.hamed.common.domain.di.CoroutinesModule
import com.hamed.common.domain.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutinesModule::class],
)
object TestDispatchersModule {
    @Provides
    @IoDispatcher
    fun providesIODispatcher(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
