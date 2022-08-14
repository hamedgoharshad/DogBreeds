package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.model.Bookmark
import com.near.common.domain.usecase.CoroutineUseCase
import com.near.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String?, List<Bookmark>>(ioDispatcher) {
    override suspend fun execute(parameters: String?): List<Bookmark> =
        bookmarkRepository.getBookmarks(parameters).run {
            if (!isEmpty()) this else throw Exception("There is nothing here")
        }
}