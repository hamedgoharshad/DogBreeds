package com.near.di

import android.content.Context
import androidx.paging.PagingConfig
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.near.datasource.local.LocalNearbyDatasourceImpl
import com.near.datasource.local.LocalPlaceDetailDataSourceImpl
import com.near.datasource.local.LocalRemoteKeyDatasourceImpl
import com.near.datasource.local.LocationDatasourceImpl
import com.near.datasource.local.interfaces.LocalNearbyDatasource
import com.near.datasource.local.interfaces.LocalPlaceDetailDataSource
import com.near.datasource.local.interfaces.LocationDatasource
import com.near.datasource.local.interfaces.RemoteKeyDatasource
import com.near.datasource.remote.RemoteBreedsDatasourceImpl
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.repository.BreedsRepository
import com.near.repository.BreedsRepositoryImpl
import com.near.utils.INITIAL_LOAD_SIZE
import com.near.utils.LocationUtilsImpl
import com.near.webApi.service.BreedsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NearbyModule {

    companion object {
        @Provides
        fun provideFusedLocationProvider(@ApplicationContext context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        @Provides
        fun providePagingConfig() =
            PagingConfig(
                pageSize = INITIAL_LOAD_SIZE,
                initialLoadSize = INITIAL_LOAD_SIZE
            )

        @Provides
        fun provideNearbyService(retrofit: Retrofit): BreedsService =
            retrofit.create(BreedsService::class.java)

    }

    @Binds
    abstract fun bindNearbyRepository(nearbyRepositoryImpl: BreedsRepositoryImpl): BreedsRepository

    @Binds
    abstract fun bindRemoteNearbyDatasource(remoteBreedsDatasourceImpl: RemoteBreedsDatasourceImpl): RemoteBreedsDatasource

    @Binds
    abstract fun bindLocalNearbyDatasource(localNearbyDatasourceImpl: LocalNearbyDatasourceImpl): LocalNearbyDatasource

    @Binds
    abstract fun bindRemoteKeyDatasource(localRemoteKeyDatasourceImpl: LocalRemoteKeyDatasourceImpl): RemoteKeyDatasource

    @Binds
    abstract fun bindLocalPlaceDetailDatasource(localPlaceDetailDataSourceImpl: LocalPlaceDetailDataSourceImpl): LocalPlaceDetailDataSource

    @Binds
    abstract fun bindLocationDatasource(locationDatasourceImpl: LocationDatasourceImpl): LocationDatasource

    @Binds
    abstract fun bindLocationUtil(locationUtilImpl: LocationUtilsImpl): LocationUtil

}