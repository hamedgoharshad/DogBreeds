package com.near.common.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.near.common.data.persistent.preference.DataStoreConstants
import com.near.common.data.persistent.preference.GenericDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(DataStoreConstants.DATA_STORE_NAME)
        }

    @Singleton
    @Provides
    fun provideGenericDataStore(dataStore: DataStore<Preferences>): GenericDataStore {
        return GenericDataStore(dataStore)
    }
}

