package com.near.webApi.service

import com.near.webApi.response.NearbyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedsService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<NearbyResponse>
}

