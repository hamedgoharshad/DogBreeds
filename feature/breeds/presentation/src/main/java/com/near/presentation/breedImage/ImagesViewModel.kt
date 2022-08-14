package com.near.presentation.breedImage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.utils.withResult
import com.near.domain.usecase.GetBreedImagesUseCase
import com.near.presentation.breedImage.ImagesUiState.Loading
import com.near.presentation.breedImage.navigation.ImagesDestination
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
class ImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBreedImagesUseCase: GetBreedImagesUseCase
) : ViewModel() {

    private val breedName: String = checkNotNull(
        savedStateHandle[ImagesDestination.breedNameArg]
    )

    private val _imagesUiState = MutableStateFlow<ImagesUiState>(Loading)
    val imagesUiState: StateFlow<ImagesUiState> = _imagesUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getBreedImagesUseCase(breedName).withResult(
                onSuccess = { ImagesList ->
                    _imagesUiState.update {
                        ImagesUiState.Success(ImagesList)
                    }
                }, onFailure = { throwable ->
                    _imagesUiState.update {
                        ImagesUiState.Failed(Exception(throwable))
                    }
                }
            )
        }
    }
}

@Immutable
sealed class ImagesUiState {
    object Loading : ImagesUiState()
    data class Success(val urls: List<String>) : ImagesUiState()
    data class Failed(val exception: Exception) : ImagesUiState()
}