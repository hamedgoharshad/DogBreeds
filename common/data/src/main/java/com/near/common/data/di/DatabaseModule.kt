package com.near.common.data.di

import android.content.Context
import androidx.room.Room
import com.near.common.data.BuildConfig
import com.near.common.data.persistent.database.NearbyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NearbyDatabase::class.java,
        BuildConfig.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providePlaceDao(appDatabase: NearbyDatabase) = appDatabase.getPlaceDao()

    @Provides
    @Singleton
    fun providePlaceDetailDao(appDatabase: NearbyDatabase) = appDatabase.getPlaceDetailDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(appDatabase: NearbyDatabase) = appDatabase.getRemoteKeyDao()

}
