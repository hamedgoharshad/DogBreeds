package com.near.datasource.local.interfaces

import com.near.common.data.persistent.database.entity.RemoteKey

interface RemoteKeyDatasource {
    suspend fun getByQuery(latLong: String): RemoteKey?
    suspend fun deleteByQuery(latLong: String)
    suspend fun insertOrReplace(remoteKey: RemoteKey)
}