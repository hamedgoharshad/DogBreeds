package com.near.webApi.service

import com.near.webApi.response.PlaceDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceDetailService {
        @GET("/v3/places/{id}")
        suspend fun getPlaceDetail(
            @Path("id") id: String,
        ): Response<PlaceDetailResponse>
}