package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.usecase.CoroutineUseCase
import com.near.domain.model.PlaceDetail
import com.near.domain.repository.PlaceDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPlaceDetailUseCase @Inject constructor(
    private val placeDetailRepository: PlaceDetailRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, PlaceDetail>(ioDispatcher) {
    override suspend fun execute(parameters: String): PlaceDetail =
        placeDetailRepository.getPlaceDetail(parameters)
}