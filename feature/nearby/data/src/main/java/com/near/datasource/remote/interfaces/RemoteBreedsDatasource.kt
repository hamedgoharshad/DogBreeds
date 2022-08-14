package com.near.datasource.remote.interfaces

import com.near.common.data.persistent.database.entity.RemoteKey
import com.near.common.domain.utils.Result
import com.near.domain.model.Breed

interface RemoteBreedsDatasource {
    suspend fun getAllBreeds(): Result<List<Breed>>
}