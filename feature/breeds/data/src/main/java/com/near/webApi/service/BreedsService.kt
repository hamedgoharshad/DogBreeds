package com.near.webApi.service

import com.near.webApi.response.ImagesResponse
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<ResponseBody>

    @GET("breed/{breed}/images")
    suspend fun getImages(
        @Path("breed") breed: String,
    ): Response<ImagesResponse>
}

