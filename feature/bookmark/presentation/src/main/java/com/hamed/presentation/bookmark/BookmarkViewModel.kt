package com.hamed.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.utils.Result
import com.hamed.common.domain.utils.ifNullReturn
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
                val data = result.data
                BookmarkUiState.Success(data.map { it.breed },
                    filter.ifNullReturn(data) { data.filter { it.breed == filter } })
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
    data class Success(val bookmarkedBreeds: List<String>, val filtered: List<Bookmark>) :
        BookmarkUiState()

    data class Failed(val exception: Exception) : BookmarkUiState()
}