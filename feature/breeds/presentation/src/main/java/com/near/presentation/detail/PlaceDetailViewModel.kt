package com.near.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.utils.Result
import com.near.domain.usecase.GetAllBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _placeDetail = MutableStateFlow<Result<PlaceDetail>>(Result.Loading)
    val placeDetail: StateFlow<Result<PlaceDetail>> get() = _placeDetail

    init {
        getPlaceDetails()
    }

    fun getPlaceDetails() {
        viewModelScope.launch {
            savedStateHandle.get<String>(PLACE_ID_KEY)?.let { id ->
                _placeDetail.update { getAllBreedsUseCase(id) }
            }
        }
    }

    companion object {
        const val PLACE_ID_KEY = "id"
    }
}