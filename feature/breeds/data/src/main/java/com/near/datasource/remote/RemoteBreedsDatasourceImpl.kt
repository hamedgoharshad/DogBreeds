package com.near.datasource.remote

import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import com.near.webApi.service.BreedsService
import javax.inject.Inject

class RemoteBreedsDatasourceImpl @Inject constructor(
    private val service: BreedsService
) : RemoteBreedsDatasource {
    override suspend fun getAllBreeds(): List<Breed> =
        service.getBreeds().body()?.let { jsonObject ->
            val allBreedsList = mutableListOf<Breed>()
            jsonObject.getJSONObject(MESSAGE_LABEL).keys().forEach { key ->
                allBreedsList.add(Breed(key))
            }
            allBreedsList.toList()
        } ?: run {
            emptyList()
        }

    override suspend fun getImages(breed: String): List<String> =
        service.getImages(breed).body()?.message ?: kotlin.run { emptyList() }

    companion object {
        const val MESSAGE_LABEL = "message"
    }
}


