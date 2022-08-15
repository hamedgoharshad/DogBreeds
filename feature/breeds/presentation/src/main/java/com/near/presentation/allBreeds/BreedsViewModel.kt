package com.near.presentation.allBreeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.utils.Result
import com.near.common.domain.utils.data
import com.near.common.domain.utils.withResult
import com.near.domain.model.Breed
import com.near.domain.usecase.GetAllBreedsUseCase
import com.near.presentation.allBreeds.BreedsUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {
    val breedsUiState: StateFlow<BreedsUiState> = flow {
        val result = getAllBreedsUseCase(Unit)
        emit(
            when (result) {
                is Result.Success -> {
                    BreedsUiState.Success(result.data)
                }
                is Result.Failure -> {
                    BreedsUiState.Failed(Exception(result.error))
                }
                Result.Loading -> Loading
            }
        )
        Timber.tag("hamed").d(result.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Loading
    )
}

@Immutable
sealed class BreedsUiState {
    object Loading : BreedsUiState()
    data class Success(val breeds: List<Breed>) : BreedsUiState()
    data class Failed(val exception: Exception) : BreedsUiState()
}