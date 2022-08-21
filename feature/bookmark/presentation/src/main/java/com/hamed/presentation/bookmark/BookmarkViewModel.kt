package com.hamed.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.utils.Result
import com.hamed.domain.repository.usecase.GetBookmarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    getAllBookmarksUseCase: GetBookmarksUseCase
) : ViewModel() {

    private val breedFilter = MutableStateFlow<String?>(null)

    val bookmarkUiState: StateFlow<BookmarkUiState> = combine(
        getAllBookmarksUseCase(null), breedFilter
    ) { result, filter ->
        when (result) {
            is Result.Success -> {
                BookmarkUiState.Success(result.data, filter)
            }
            is Result.Failure -> {
                BookmarkUiState.Failed(result.error)
            }
            Result.Loading -> BookmarkUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BookmarkUiState.Loading
    )

    fun onFiltered(filter: String) {
        breedFilter.update { filter }
    }
}

@Immutable
sealed class BookmarkUiState {
    object Loading : BookmarkUiState()
    data class Success(val bookmarks: List<Bookmark>, val filter: String?) : BookmarkUiState()
    data class Failed(val exception: Exception) : BookmarkUiState()
}