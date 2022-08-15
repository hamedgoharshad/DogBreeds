package com.near.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.model.Bookmark
import com.near.common.domain.utils.Result
import com.near.domain.usecase.GetBookmarksUseCase
import com.near.presentation.allBreeds.BreedsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    getAllBookmarksUseCase: GetBookmarksUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val bookmarkUiState: StateFlow<BookmarkUiState> =
        getAllBookmarksUseCase(null).mapLatest { result ->
            when (result) {
                is Result.Success -> {
                    BookmarkUiState.Success(result.data)
                }
                is Result.Failure -> {
                    BookmarkUiState.Failed(Exception(result.error))
                }
                Result.Loading -> BookmarkUiState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BookmarkUiState.Loading
        )
}

@Immutable
sealed class BookmarkUiState {
    object Loading : BookmarkUiState()
    data class Success(val bookmarks: List<Bookmark>) : BookmarkUiState()
    data class Failed(val exception: Exception) : BookmarkUiState()
}