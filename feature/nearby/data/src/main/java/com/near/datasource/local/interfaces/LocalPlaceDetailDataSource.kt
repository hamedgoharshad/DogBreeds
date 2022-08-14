package com.near.datasource.local.interfaces

import com.near.common.data.persistent.database.entity.PlaceDetailEntity

interface LocalPlaceDetailDataSource {
    suspend fun getPlaceDetail(id:String):PlaceDetailEntity?
    suspend fun insertPlaceDetail(placeDetail:PlaceDetailEntity)
}