package com.near.datasource.remote

import com.near.datasource.remote.interfaces.RemotePlaceDetailDatasource
import com.near.webApi.response.PlaceDetailResponse
import com.near.webApi.service.PlaceDetailService
import retrofit2.Response
import javax.inject.Inject

class RemotePlaceDetailDatasourceImpl @Inject constructor(private val placeDetailService: PlaceDetailService) :
    RemotePlaceDetailDatasource {
    override suspend fun getPlaceDetail(id: String): Response<PlaceDetailResponse> =
        placeDetailService.getPlaceDetail(id)
}