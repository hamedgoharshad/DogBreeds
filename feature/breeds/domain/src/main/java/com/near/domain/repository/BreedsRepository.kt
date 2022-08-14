package com.near.domain.repository

import com.near.domain.model.Breed

interface BreedsRepository {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getImages(breed: String): List<String>
}
