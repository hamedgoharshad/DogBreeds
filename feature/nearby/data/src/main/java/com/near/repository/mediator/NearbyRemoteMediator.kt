package com.near.repository.mediator

import android.location.Location
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.common.data.persistent.database.entity.RemoteKey
import com.near.common.domain.utils.bodyOrThrow
import com.near.datasource.local.interfaces.LocalNearbyDatasource
import com.near.datasource.local.interfaces.RemoteKeyDatasource
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.mapper.NearbyResponseMapper.map
import com.near.mapper.mapToString
import com.near.webApi.response.NearbyResponse
import retrofit2.Response
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NearbyRemoteMediator @Inject constructor(
    private val remoteKeyDatasource: RemoteKeyDatasource,
    private val remoteBreedsDatasource: RemoteBreedsDatasource,
    private val localNearbyDatasource: LocalNearbyDatasource,
) : RemoteMediator<Int, PlaceEntity>() {

    data class Param(
        val location: Location?,
        val query: String
    )

    private var location: Location? = null
    private lateinit var query: String

    operator fun invoke(param: Param): NearbyRemoteMediator = apply {
        location = param.location
        query = param.query
    }

    override suspend fun initialize() =
        if (remoteKeyDatasource.getByQuery(location?.mapToString() ?: String()) == null)
            InitializeAction.LAUNCH_INITIAL_REFRESH
        else
            InitializeAction.SKIP_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlaceEntity>
    ): MediatorResult {
        return try {
            val loadKey: RemoteKey = when (loadType) {
                REFRESH -> {
                    RemoteKey(
                        location!!.mapToString(),
                        String(),
                        state.config.initialLoadSize,
                        query
                    )
                }
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    val remoteKey = remoteKeyDatasource.getByQuery(location!!.mapToString())
                    if (remoteKey == null || remoteKey.cursor.isEmpty())
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    else remoteKey
                }
            }
            val response: Response<NearbyResponse> =
                remoteBreedsDatasource.getNearbyPlaces(loadKey)
            localNearbyDatasource.getDatabase().withTransaction {
                if (loadType == REFRESH) {
                    remoteKeyDatasource.deleteByQuery(loadKey.latLong)
                    localNearbyDatasource.deleteAll()
                }
                localNearbyDatasource.insertAll(response.bodyOrThrow().map())
                remoteKeyDatasource.insertOrReplace(
                    loadKey.run {
                        copy(latLong, getCursor(response), limit, query)
                    }
                )
            }
            MediatorResult.Success(
                endOfPaginationReached = getCursor(response).isEmpty()
            )
        } catch (e: Throwable) {
            return MediatorResult.Error(e)
        }
    }

    private fun getCursor(response: Response<NearbyResponse>): String =
        response.headers()["link"]?.substringAfter("&cursor=")?.substringBefore("&") ?: String()
}