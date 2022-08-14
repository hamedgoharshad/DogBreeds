package com.near.datasource.remote.interfaces

import com.near.webApi.response.PlaceDetailResponse
import retrofit2.Response

interface RemotePlaceDetailDatasource {
    suspend fun getPlaceDetail(id: String): Response<PlaceDetailResponse>
}