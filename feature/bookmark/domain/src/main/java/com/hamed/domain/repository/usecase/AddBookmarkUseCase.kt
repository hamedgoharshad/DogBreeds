package com.hamed.domain.repository.usecase

import com.hamed.common.domain.di.IoDispatcher
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.domain.baseUsecase.CoroutineUseCase
import com.hamed.domain.repository.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Bookmark, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Bookmark): Unit =
        bookmarkRepository.addBookmark(parameters)
}