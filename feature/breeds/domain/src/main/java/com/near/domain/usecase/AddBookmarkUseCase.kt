package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.model.Bookmark
import com.near.common.domain.usecase.CoroutineUseCase
import com.near.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Bookmark, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Bookmark): Unit =
        bookmarkRepository.addBookmark(parameters)
}