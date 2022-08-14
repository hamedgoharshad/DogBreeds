package com.near.di

import android.content.Context
import com.near.utils.DISPLACEMENT_IN_METER
import com.near.utils.FASTEST_REQUEST_INTERVAL
import com.near.utils.LOCATION_REQUEST_INTERVAL
import com.google.android.gms.location.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun provideLocationRequest(): LocationRequest = LocationRequest.create().apply {
        interval = LOCATION_REQUEST_INTERVAL
        fastestInterval = FASTEST_REQUEST_INTERVAL
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        smallestDisplacement = DISPLACEMENT_IN_METER
    }

    @Provides
    fun provideLocationSettingsRequestBuilder(locationRequest: LocationRequest):
            LocationSettingsRequest =
        LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest).build()

    @Provides
    fun provideLocationSettingsClient(@ApplicationContext context: Context): SettingsClient =
        LocationServices.getSettingsClient(context)
}