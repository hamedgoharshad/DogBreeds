package com.hamed.datasource.remote.interfaces

import com.hamed.domain.model.Breed

internal interface RemoteBreedsDatasource {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getImages(breed: String):List<String>
}