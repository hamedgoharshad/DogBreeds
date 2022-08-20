package com.hamed.common.data.di

import android.content.Context
import androidx.room.Room
import com.hamed.common.data.BuildConfig
import com.hamed.common.data.persistent.database.BookmarkDatabase
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
        BookmarkDatabase::class.java,
        BuildConfig.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providePlaceDao(appDatabase: BookmarkDatabase) = appDatabase.getBookmarkDao()
}
