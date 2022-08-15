package com.near.di

import com.near.datasource.local.BookmarkLocalDatasourceImpl
import com.near.datasource.local.interfaces.BookmarkLocalDatasource
import com.near.datasource.remote.RemoteBreedsDatasourceImpl
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.repository.BookmarkRepository
import com.near.domain.repository.BreedsRepository
import com.near.repository.BookmarkRepositoryImpl
import com.near.repository.BreedsRepositoryImpl
import com.near.webApi.service.BreedsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NearbyModule {

    companion object {
        @Provides
        fun provideBreedsService(retrofit: Retrofit): BreedsService =
            retrofit.create(BreedsService::class.java)
    }

    @Binds
    abstract fun bindBreedsRepository(breedsRepository: BreedsRepositoryImpl): BreedsRepository

    @Binds
    abstract fun bindRemoteBreedsDatasource(remoteBreedsDatasourceImpl: RemoteBreedsDatasourceImpl): RemoteBreedsDatasource

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository

    @Binds
    abstract fun bindRemoteNearbyDatasource(bookmarkLocalDatasourceImpl: BookmarkLocalDatasourceImpl): BookmarkLocalDatasource


}