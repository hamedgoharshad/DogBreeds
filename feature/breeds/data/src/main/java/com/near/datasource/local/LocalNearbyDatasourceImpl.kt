package com.near.datasource.local

import com.near.common.data.persistent.database.NearbyDatabase
import com.near.common.data.persistent.database.dao.PlaceDao
import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.datasource.local.interfaces.LocalNearbyDatasource
import javax.inject.Inject

class LocalNearbyDatasourceImpl @Inject constructor(
    private val placeDao: PlaceDao,
    private val database: NearbyDatabase
) : LocalNearbyDatasource {
    override fun getPagingSource() =
        placeDao.pagingSource()

    override suspend fun insertAll(places: List<PlaceEntity>) =
        placeDao.insertAll(places)

    override suspend fun deleteAll() =
        placeDao.deleteAll()

    override fun getDatabase(): NearbyDatabase = database
}
