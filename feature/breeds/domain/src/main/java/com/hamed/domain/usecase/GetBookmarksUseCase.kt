package com.hamed.domain.usecase

import com.hamed.common.domain.di.IoDispatcher
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.usecase.FlowUseCase
import com.hamed.common.domain.utils.Result
import com.hamed.domain.repository.BookmarkRepository
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