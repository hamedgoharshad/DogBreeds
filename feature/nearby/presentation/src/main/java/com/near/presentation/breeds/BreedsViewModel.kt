package com.near.presentation.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.utils.mapWith
import com.near.common.domain.utils.withResult
import com.near.domain.model.Breed
import com.near.domain.usecase.GetAllBreedsUseCase
import com.near.presentation.breeds.BreedsUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {

    private val _breedsUiState = MutableStateFlow<BreedsUiState>(Loading)
    val breedsUiState: StateFlow<BreedsUiState> = _breedsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllBreedsUseCase(Unit).withResult(
                onSuccess = { breedsList ->
                    _breedsUiState.update {
                        BreedsUiState.Success(breedsList)
                    }
                }, onFailure = { throwable ->
                    _breedsUiState.update {
                        BreedsUiState.Failed(Exception(throwable))
                    }
                }
            )
        }
    }
}

@Immutable
sealed class BreedsUiState {
    object Loading : BreedsUiState()
    data class Success(val breeds: List<Breed>) : BreedsUiState()
    data class Failed(val exception: Exception) : BreedsUiState()
}