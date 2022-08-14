package com.near.repository

import com.near.common.domain.utils.bodyOrThrow
import com.near.common.domain.utils.networkWithCache
import com.near.datasource.local.interfaces.LocalPlaceDetailDataSource
import com.near.datasource.remote.interfaces.RemotePlaceDetailDatasource
import com.near.domain.model.PlaceDetail
import com.near.domain.repository.PlaceDetailRepository
import com.near.mapper.PlaceDetailMapper.map
import com.near.mapper.PlaceDetailResponseMapper.map
import javax.inject.Inject

class PlaceDetailRepositoryImpl @Inject constructor(
    private val localPlaceDetailDataSource: LocalPlaceDetailDataSource,
    private val remotePlaceDetailDatasource: RemotePlaceDetailDatasource
) : PlaceDetailRepository {
    override suspend fun getPlaceDetail(id: String): PlaceDetail =
        networkWithCache(
            networkCall = {
                remotePlaceDetailDatasource.getPlaceDetail(id).bodyOrThrow().map()
            },
            loadFromLocal = {
                localPlaceDetailDataSource.getPlaceDetail(id)
            },
            shouldFetch = {
                it == null
            },
            { entity ->
                localPlaceDetailDataSource.insertPlaceDetail(entity)
            }
        ).map()
}