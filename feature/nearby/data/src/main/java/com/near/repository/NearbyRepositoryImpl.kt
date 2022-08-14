package com.near.repository

import androidx.paging.*
import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.datasource.local.interfaces.LocalNearbyDatasource
import com.near.domain.model.Place
import com.near.domain.model.SimpleLocation
import com.near.domain.repository.NearbyRepository
import com.near.mapper.PlaceEntityMapper.map
import com.near.mapper.mapToLocation
import com.near.repository.mediator.NearbyRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NearbyRepositoryImpl @Inject constructor(
    private val localNearbyDatasource: LocalNearbyDatasource,
    private val nearbyRemoteMediator: NearbyRemoteMediator,
) : NearbyRepository {
    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
    override fun getNearbyPlaces(
        pagingConfig: PagingConfig,
        location: SimpleLocation?,
        query:String
    ): Flow<PagingData<Place>> = Pager(
        config = pagingConfig,
        remoteMediator = nearbyRemoteMediator(NearbyRemoteMediator.Param(location?.mapToLocation(),query)),
        pagingSourceFactory = { localNearbyDatasource.getPagingSource() }
    ).flow.mapLatest { pagingData: PagingData<PlaceEntity> ->
        pagingData.map { placeEntity ->
            placeEntity.map()
        }
    }
}