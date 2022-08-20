package com.hamed.webApi.service

import com.hamed.webApi.response.ImagesResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface BreedsService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<ResponseBody>

    @GET("breed/{breed}/images")
    suspend fun getImages(
        @Path("breed") breed: String,
    ): Response<ImagesResponse>
}

