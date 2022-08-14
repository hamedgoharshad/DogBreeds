package com.near.domain.repository

import com.near.domain.model.PlaceDetail

interface PlaceDetailRepository {
    suspend fun getPlaceDetail(id:String):PlaceDetail
}