package com.near.repository

import com.near.common.domain.utils.Result
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.domain.model.Breed
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val remoteBreedsDatasource: RemoteBreedsDatasource) :
    BreedsRepository {
    override suspend fun getAllBreeds(): Result<List<Breed>> =
        remoteBreedsDatasource.getAllBreeds()
}