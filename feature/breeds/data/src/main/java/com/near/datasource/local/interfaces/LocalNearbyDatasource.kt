package com.near.datasource.local.interfaces

import androidx.paging.PagingSource
import com.near.common.data.persistent.database.NearbyDatabase
import com.near.common.data.persistent.database.entity.PlaceEntity

interface LocalNearbyDatasource {
    fun getPagingSource(): PagingSource<Int, PlaceEntity>
    suspend fun insertAll(places: List<PlaceEntity>)
    suspend fun deleteAll()
    fun getDatabase(): NearbyDatabase
}