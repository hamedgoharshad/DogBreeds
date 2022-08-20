package com.hamed.repository

import com.hamed.datasource.remote.interfaces.RemoteBreedsDatasource
import com.hamed.domain.model.Breed
import com.hamed.domain.repository.BreedsRepository
import javax.inject.Inject

internal class BreedsRepositoryImpl @Inject constructor(private val remoteBreedsDatasource: RemoteBreedsDatasource) :
    BreedsRepository {
    override suspend fun getAllBreeds(): List<Breed> =
        remoteBreedsDatasource.getAllBreeds()

    override suspend fun getImages(breed: String): List<String> =
        remoteBreedsDatasource.getImages(breed)
}