package com.near.presentation.nearby

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.near.common.domain.utils.onSuccess
import com.near.domain.usecase.GetLocationUpdatesUsecase
import com.near.domain.usecase.GetNearbyPlacesUsecase
import com.near.domain.usecase.GetNearbyPlacesUsecase.Params
import com.near.mapper.mapToSimpleLocation
import com.near.mapper.mapToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class NearbyViewModel @Inject constructor(
    private val getNearbyPlacesUsecase: GetNearbyPlacesUsecase,
    private val getLocationUpdatesUsecase: GetLocationUpdatesUsecase,
    private val pagingConfig: PagingConfig,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val location = getLocationUpdatesUsecase(Unit)
        .onEach {
            it.onSuccess { simpleLocation ->
                savedStateHandle[LOCATION_KEY_STATE] = simpleLocation.mapToString()
            }
        }

    val nearbyPlaces = savedStateHandle.getLiveData<String>(LOCATION_KEY_STATE)
        .asFlow()
        .map {
            it.mapToSimpleLocation()
        }
        .distinctUntilChanged()
        .flatMapLatest {
            getNearbyPlacesUsecase(
                Params(
                    it,
                    TEMP_QUERY,
                    pagingConfig
                )
            )
        }
        .cachedIn(viewModelScope)

    companion object {
        const val LOCATION_KEY_STATE = "location_state"
        const val TEMP_QUERY = "pizza"
    }
}
