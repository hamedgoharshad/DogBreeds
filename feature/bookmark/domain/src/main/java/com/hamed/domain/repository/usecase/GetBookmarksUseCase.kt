package com.hamed.domain.repository.usecase

import com.hamed.common.domain.di.IoDispatcher
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.baseUsecase.FlowUseCase
import com.hamed.domain.repository.repository.BookmarkRepository
import com.hamed.common.domain.utils.Result
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