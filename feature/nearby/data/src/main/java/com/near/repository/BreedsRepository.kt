package com.near.repository

import com.near.common.domain.utils.Result
import com.near.domain.model.Breed

interface BreedsRepository {
    suspend fun getAllBreeds(): Result<List<Breed>>
}