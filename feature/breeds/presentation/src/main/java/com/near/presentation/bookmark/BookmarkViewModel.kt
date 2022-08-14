package com.near.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.utils.Result
import com.near.common.domain.utils.withResult
import com.near.domain.model.Breed
import com.near.domain.usecase.GetAllBreedsUseCase
import com.near.presentation.allBreeds.BreedsUiState.Loading
import com.near.presentation.breedImages.ImagesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {

    val breedsUiState: StateFlow<BreedsUiState> = flow {
        emit(
            when (val result = getAllBreedsUseCase(Unit)) {
                is Result.Success -> {
                    BreedsUiState.Success(result.data)
                }
                is Result.Failure -> {
                    BreedsUiState.Failed(Exception(result.error))
                }
                Result.Loading -> BreedsUiState.Loading
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BreedsUiState.Loading
    )
}

@Immutable
sealed class BreedsUiState {
    object Loading : BreedsUiState()
    data class Success(val breeds: List<Breed>) : BreedsUiState()
    data class Failed(val exception: Exception) : BreedsUiState()
}