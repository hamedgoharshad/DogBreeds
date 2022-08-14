package com.near.presentation.breedImage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.domain.model.Breed
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
    private val getAllImagesUseCase: GetAllImagesUseCase
) : ViewModel() {

    private val _ImagesUiState = MutableStateFlow<ImagesUiState>(Loading)
    val ImagesUiState: StateFlow<ImagesUiState> = _ImagesUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllImagesUseCase(Unit).withResult(
                onSuccess = { ImagesList ->
                    _ImagesUiState.update {
                        ImagesUiState.Success(ImagesList)
                    }
                }, onFailure = { throwable ->
                    _ImagesUiState.update {
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
    data class Success(val Images: List<Breed>) : ImagesUiState()
    data class Failed(val exception: Exception) : ImagesUiState()
}