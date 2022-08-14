package com.near.datasource.local.interfaces

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationDatasource {
    val savedLocation: Flow<Location>
    fun getFreshLocation(): Flow<Location>
    suspend fun saveLastLocation(location: Location)
}