package com.near.datasource.remote

import com.near.common.data.persistent.database.entity.RemoteKey
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.webApi.response.NearbyResponse
import com.near.webApi.service.BreedsService
import retrofit2.Response
import javax.inject.Inject

class RemoteBreedsDatasourceImpl @Inject constructor(
    private val service: BreedsService
) : RemoteBreedsDatasource {
    override suspend fun getNearbyPlaces(remoteKey: RemoteKey): Response<NearbyResponse> =
        service.getBreeds()
}
