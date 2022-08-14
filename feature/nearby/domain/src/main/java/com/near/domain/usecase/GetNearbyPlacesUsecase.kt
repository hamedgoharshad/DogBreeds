package com.near.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.usecase.PagingUseCase
import com.near.domain.model.Place
import com.near.domain.model.SimpleLocation
import com.near.domain.repository.NearbyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetNearbyPlacesUsecase @Inject constructor(
    private val nearbyRepository: NearbyRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : PagingUseCase<GetNearbyPlacesUsecase.Params, PagingData<Place>>(ioDispatcher) {
    override fun execute(parameters: Params): Flow<PagingData<Place>> =
        nearbyRepository.getNearbyPlaces(
            parameters.pagingConfig,
            parameters.location,
            parameters.query
        )

    data class Params(
        val location: SimpleLocation,
        val query: String,
        val pagingConfig: PagingConfig
    )
}
