package com.hamed.presentation.breedImages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.utils.*
import com.hamed.domain.model.Breed
import com.hamed.domain.usecase.AddBookmarkUseCase
import com.hamed.domain.usecase.DeleteBookmarksUseCase
import com.hamed.domain.repository.usecase.GetBookmarksUseCase
import com.hamed.domain.usecase.GetBreedImagesUseCase
import com.hamed.presentation.breedImages.ImagesUiState.Loading
import com.hamed.presentation.breedImages.navigation.ImagesDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBookmarksUseCase: GetBookmarksUseCase,
    private val getBreedImagesUseCase: GetBreedImagesUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val deleteBookmarksUseCase: DeleteBookmarksUseCase
) : ViewModel() {

    private val breedName: String = checkNotNull(
        savedStateHandle[ImagesDestination.breedNameArg]
    )

    val imagesUiState: StateFlow<ImagesUiState> = combine(
        getBookmarksUseCase(breedName),
        flow { emit(getBreedImagesUseCase(breedName)) })
    { bookmarks: Result<List<Bookmark>>, images: Result<List<String>> ->
        when (images) {
            is Result.Success -> {
                ImagesUiState.Success(
                    images.successOr(emptyList()),
                    bookmarks.successOr(emptyList()),
                    Breed(breedName)
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

    fun deleteBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            deleteBookmarksUseCase(bookmark)
        }
    }


}

@Immutable
sealed class ImagesUiState {
    object Loading : ImagesUiState()

    data class Success(val urls: List<String>, val bookmarks: List<Bookmark>, val breed: Breed) :
        ImagesUiState()

    data class Failed(val exception: Exception) : ImagesUiState()
}