package com.near.datasource.local

import com.near.common.data.persistent.database.dao.PlaceDetailDao
import com.near.common.data.persistent.database.entity.PlaceDetailEntity
import com.near.datasource.local.interfaces.LocalPlaceDetailDataSource
import javax.inject.Inject

class LocalPlaceDetailDataSourceImpl @Inject constructor(private val placeDetailDao: PlaceDetailDao) :
    LocalPlaceDetailDataSource {
    override suspend fun getPlaceDetail(id: String): PlaceDetailEntity? = placeDetailDao.get(id)
    override suspend fun insertPlaceDetail(placeDetail: PlaceDetailEntity) =
        placeDetailDao.insert(placeDetail)

}