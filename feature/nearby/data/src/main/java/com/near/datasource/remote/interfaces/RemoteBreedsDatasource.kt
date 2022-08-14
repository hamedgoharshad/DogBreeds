package com.near.datasource.remote.interfaces

import com.near.common.data.persistent.database.entity.RemoteKey
import com.near.webApi.response.NearbyResponse
import retrofit2.Response

interface RemoteBreedsDatasource {
    suspend fun getNearbyPlaces(remoteKey: RemoteKey): Response<NearbyResponse>
}