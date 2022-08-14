package com.near.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.near.domain.model.Place
import com.near.domain.model.SimpleLocation
import kotlinx.coroutines.flow.Flow

interface NearbyRepository {
    fun getNearbyPlaces(
        pagingConfig: PagingConfig,
        location: SimpleLocation?,
        query:String
    ): Flow<PagingData<Place>>
}