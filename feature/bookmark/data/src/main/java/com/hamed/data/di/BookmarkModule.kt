package com.hamed.data.di

import com.hamed.data.local.BookmarkLocalDatasourceImpl
import com.hamed.data.local.interfaces.BookmarkLocalDatasource
import com.hamed.data.repository.BookmarkRepositoryImpl
import com.hamed.domain.repository.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class BookmarkModule {

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository

    @Binds
    abstract fun bindBookmarkLocalDatasource(bookmarkLocalDatasourceImpl: BookmarkLocalDatasourceImpl): BookmarkLocalDatasource
}