package com.near.datasource.remote

import com.near.common.domain.utils.ifNullReturn
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import com.near.webApi.service.BreedsService
import javax.inject.Inject

class RemoteBreedsDatasourceImpl @Inject constructor(
    private val service: BreedsService
) : RemoteBreedsDatasource {
    override suspend fun getAllBreeds(): List<Breed> =
        service.getBreeds().body().ifNullReturn(emptyList()) { jsonObject ->
            val allBreedsList = mutableListOf<Breed>()
            val message = jsonObject.getJSONObject(MESSAGE_LABEL)
            message.keys().forEach { key: String ->
                allBreedsList.add(Breed(key))
                // todo: check whether it includes values or not
            }
            allBreedsList.toList()
        }

    override suspend fun getImages(breed: String): List<String> =
        service.getImages(breed).body()?.message ?: kotlin.run { emptyList() }

    companion object {
        const val MESSAGE_LABEL = "message"
    }
}

