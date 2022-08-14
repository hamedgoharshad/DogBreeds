package com.near.repository

import android.location.Location
import com.near.datasource.local.interfaces.LocationDatasource
import com.near.domain.model.SimpleLocation
import com.near.domain.repository.LocationRepository
import com.near.mapper.mapToLocation
import com.near.mapper.mapToSimpleLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationDatasource: LocationDatasource) :
    LocationRepository {

    override val savedLocation: Flow<SimpleLocation> =
        locationDatasource.savedLocation.map(Location::mapToSimpleLocation)

    override fun getFreshLocation(): Flow<SimpleLocation> =
        locationDatasource.getFreshLocation().map(Location::mapToSimpleLocation)

    override suspend fun saveLastLocation(simpleLocation: SimpleLocation) {
            locationDatasource.saveLastLocation(simpleLocation.mapToLocation())
    }
}

