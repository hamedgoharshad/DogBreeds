package com.near.domain.usecase

import com.near.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Bookmark, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Bookmark): Unit =
        bookmarkRepository.deleteBookmark(parameters)
}