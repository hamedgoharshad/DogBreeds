package com.near.datasource.remote.interfaces

import com.near.domain.model.Breed

interface RemoteBreedsDatasource {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getImages(breed: Breed):List<String>
}