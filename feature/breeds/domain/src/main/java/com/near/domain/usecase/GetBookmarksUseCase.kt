package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.model.Bookmark
import com.near.common.domain.usecase.FlowUseCase
import com.near.common.domain.utils.Result
import com.near.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String?, List<Bookmark>>(ioDispatcher) {
    override fun execute(parameters: String?): Flow<Result<List<Bookmark>>> =
        bookmarkRepository.getBookmarks(parameters).map {
            Result.Success(it)
        }
}