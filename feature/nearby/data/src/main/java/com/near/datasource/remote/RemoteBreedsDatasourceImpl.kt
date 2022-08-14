package com.near.datasource.remote

import com.near.common.domain.utils.Result
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import com.near.webApi.service.BreedsService
import java.lang.Exception
import javax.inject.Inject

class RemoteBreedsDatasourceImpl @Inject constructor(
    private val service: BreedsService
) : RemoteBreedsDatasource {
    override suspend fun getAllBreeds(): Result<List<Breed>> =
        service.getBreeds().body()?.let { jsonObject ->
            val allBreedsList = mutableListOf<Breed>()
            jsonObject.getJSONObject(MESSAGE_LABEL).keys().forEach { key ->
                allBreedsList.add(Breed(key))
            }
            Result.Success(allBreedsList.toList())
        } ?: run {
            Result.Failure(Exception(""))
        }

    companion object{
        const val MESSAGE_LABEL = "message"
    }
}


