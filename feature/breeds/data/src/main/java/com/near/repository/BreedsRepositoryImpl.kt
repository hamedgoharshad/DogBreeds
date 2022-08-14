package com.near.repository

import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import com.near.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val remoteBreedsDatasource: RemoteBreedsDatasource) :
    BreedsRepository {
    override suspend fun getAllBreeds(): List<Breed> =
        remoteBreedsDatasource.getAllBreeds()

    override suspend fun getImages(breed: Breed): List<String> =
        remoteBreedsDatasource.getImages(breed)

}