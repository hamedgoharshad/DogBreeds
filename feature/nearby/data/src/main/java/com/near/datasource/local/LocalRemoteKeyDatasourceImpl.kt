package com.near.datasource.local

import com.near.common.data.persistent.database.dao.RemoteKeyDao
import com.near.common.data.persistent.database.entity.RemoteKey
import com.near.datasource.local.interfaces.RemoteKeyDatasource
import javax.inject.Inject

class LocalRemoteKeyDatasourceImpl @Inject constructor(
    private val remoteKeyDao: RemoteKeyDao
) : RemoteKeyDatasource {

    override suspend fun getByQuery(latLong: String): RemoteKey =
        remoteKeyDao.getByQuery(latLong)

    override suspend fun deleteByQuery(latLong: String) =
        remoteKeyDao.deleteByQuery(latLong)

    override suspend fun insertOrReplace(remoteKey: RemoteKey) =
        remoteKeyDao.insertOrReplace(remoteKey)
}