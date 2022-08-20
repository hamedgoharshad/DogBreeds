package com.hamed.data.di

import com.hamed.common.data.datasource.local.BookmarkLocalDatasourceImpl
import com.hamed.common.data.datasource.local.interfaces.BookmarkLocalDatasource
import com.hamed.data.repository.BookmarkRepositoryImpl
import com.hamed.domain.repository.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class BreedsModule {

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository

    @Binds
    abstract fun bindBookmarkLocalDatasource(bookmarkLocalDatasourceImpl: BookmarkLocalDatasourceImpl): BookmarkLocalDatasource
}