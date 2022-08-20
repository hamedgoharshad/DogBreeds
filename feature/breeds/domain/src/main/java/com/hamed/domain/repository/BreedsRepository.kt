package com.hamed.domain.repository

import com.hamed.domain.model.Breed

interface BreedsRepository {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getImages(breed: String): List<String>
}