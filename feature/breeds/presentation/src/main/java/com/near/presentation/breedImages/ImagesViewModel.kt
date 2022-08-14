package com.near.presentation.breedImages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.model.Bookmark
import com.near.common.domain.utils.*
import com.near.domain.usecase.AddBookmarkUseCase
import com.near.domain.usecase.GetBookmarksUseCase
import com.near.domain.usecase.GetBreedImagesUseCase
import com.near.presentation.breedImages.ImagesUiState.Loading
import com.near.presentation.breedImages.navigation.ImagesDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBreedImagesUseCase: GetBreedImagesUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase
) : ViewModel() {

    val breedName: String = checkNotNull(
        savedStateHandle[ImagesDestination.breedNameArg]
    )

    val imagesUiState: StateFlow<ImagesUiState> = combine(
        flow { emit(getBookmarksUseCase(breedName)) },
        flow { emit(getBreedImagesUseCase(breedName)) })
    { bookmarks: Result<List<Bookmark>>, images: Result<List<String>> ->
        when (images) {
            is Result.Success -> {
                ImagesUiState.Success(
                    images.successOr(emptyList()),
                    bookmarks.successOr(emptyList())
                )
            }
            is Result.Failure -> {
                ImagesUiState.Failed(Exception(images.error))
            }
            Result.Loading -> {
                Loading
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Loading
    )

    fun addBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            addBookmarkUseCase(bookmark)
        }
    }
}

@Immutable
sealed class ImagesUiState {
    object Loading : ImagesUiState()
    data class Success(val urls: List<String>, val bookmarks: List<Bookmark>) : ImagesUiState()
    data class Failed(val exception: Exception) : ImagesUiState()
}