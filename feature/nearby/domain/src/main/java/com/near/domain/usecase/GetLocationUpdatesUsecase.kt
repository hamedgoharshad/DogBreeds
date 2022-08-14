package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.usecase.FlowUseCase
import com.near.common.domain.utils.Result
import com.near.domain.LocationUtil
import com.near.domain.model.SimpleLocation
import com.near.domain.repository.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetLocationUpdatesUsecase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val locationUtil: LocationUtil,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, SimpleLocation>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<SimpleLocation>> =
        locationRepository.run {
            getFreshLocation().mapLatest { fresh ->
                val cache: SimpleLocation? = savedLocation.catch { emit(null) }.first()
                if (shouldFetch(fresh, cache)) {
                    fresh.also {
                        saveLastLocation(it)
                    }
                } else cache!!
            }.map { simpleLocation -> Result.Success(simpleLocation) }
        }

    private fun shouldFetch(
        fresh: SimpleLocation?,
        cache: SimpleLocation?
    ): Boolean {
        cache ?: return true
        fresh ?: return false
        return locationUtil.distanceOf(fresh, cache) > replacementInMeter
    }

    companion object {
        const val replacementInMeter = 100
    }
}