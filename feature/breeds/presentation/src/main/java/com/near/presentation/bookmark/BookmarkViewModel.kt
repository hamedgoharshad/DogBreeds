package com.near.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.near.common.domain.model.Bookmark
import com.near.common.domain.utils.Result
import com.near.common.domain.utils.ifNullReturn
import com.near.domain.usecase.GetBookmarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.Locale.filter
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
                BookmarkUiState.Failed(Exception(result.error))
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