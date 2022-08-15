package com.near.datasource.remote

import com.near.common.domain.utils.ifNullReturn
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import com.near.webApi.service.BreedsService
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class RemoteBreedsDatasourceImpl @Inject constructor(
    private val service: BreedsService
) : RemoteBreedsDatasource {
    override suspend fun getAllBreeds(): List<Breed> =
        service.getBreeds().body().ifNullReturn(emptyList()) { jsonObject ->
            val allBreedsList = mutableListOf<Breed>()
            val message = JSONObject(jsonObject.string()).getJSONObject(MESSAGE_LABEL)
            message.keys().forEach { key: String ->
                allBreedsList.add(Breed(key))
                /*
                // for subBreeds :
                message.getJSONArray(key).let { jsonArray: JSONArray ->
                        for (i in 0 until jsonArray.length()) {
                            allBreedsList.add(Breed(jsonArray.getString(i)))
                        }
                }*/
            }
            allBreedsList.toList()
        }

    override suspend fun getImages(breed: String): List<String> =
        service.getImages(breed).body()?.message ?: kotlin.run { emptyList() }

    companion object {
        const val MESSAGE_LABEL = "message"
    }
}

