package com.near.domain.repository

import com.near.domain.model.SimpleLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
   val savedLocation: Flow<SimpleLocation?>
   fun getFreshLocation(): Flow<SimpleLocation>
   suspend fun saveLastLocation(simpleLocation: SimpleLocation)
}