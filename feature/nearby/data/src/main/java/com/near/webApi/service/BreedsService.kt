package com.near.webApi.service

import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET

interface BreedsService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<JSONObject>
}

