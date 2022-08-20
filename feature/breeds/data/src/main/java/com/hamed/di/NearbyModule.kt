package com.hamed.di

import com.hamed.datasource.local.BookmarkLocalDatasourceImpl
import com.hamed.datasource.local.interfaces.BookmarkLocalDatasource
import com.hamed.datasource.remote.RemoteBreedsDatasourceImpl
import com.hamed.datasource.remote.interfaces.RemoteBreedsDatasource
import com.hamed.domain.repository.BookmarkRepository
import com.hamed.domain.repository.BreedsRepository
import com.hamed.repository.BookmarkRepositoryImpl
import com.hamed.repository.BreedsRepositoryImpl
import com.hamed.webApi.service.BreedsService
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