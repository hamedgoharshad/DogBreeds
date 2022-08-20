package com.hamed.data.repository

import com.hamed.common.data.persistent.database.entity.BookmarkEntity
import com.hamed.common.domain.model.Bookmark
import com.hamed.common.data.datasource.local.interfaces.BookmarkLocalDatasource
import com.hamed.domain.repository.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDatasource: BookmarkLocalDatasource) :
    BookmarkRepository {
    override fun getBookmarks(breed: String?): Flow<List<Bookmark>> =
        bookmarkLocalDatasource.getBookmarks(breed).map { list -> list.map { it.toDomainModel() } }

    override suspend fun addBookmark(bookmark: Bookmark) = bookmarkLocalDatasource.addBookmark(
        BookmarkEntity.fromDomainModel(bookmark)
    )

    override suspend fun deleteBookmark(bookmark: Bookmark) =
        bookmarkLocalDatasource.deleteBookmark(
            BookmarkEntity.fromDomainModel(bookmark)
        )
}