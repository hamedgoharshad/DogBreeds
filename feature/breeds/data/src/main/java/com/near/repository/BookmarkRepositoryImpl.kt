package com.near.repository

import com.near.common.data.persistent.database.entity.BookmarkEntity
import com.near.datasource.local.interfaces.BookmarkLocalDatasource
import com.near.common.domain.model.Bookmark
import com.near.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDatasource: BookmarkLocalDatasource) :
    BookmarkRepository {
    override suspend fun getBookmarks(breed:String?): List<Bookmark> =
        bookmarkLocalDatasource.getBookmarks(breed).map { it.toDomainModel() }

    override suspend fun addBookmark(bookmark: Bookmark) = bookmarkLocalDatasource.addBookmark(
        BookmarkEntity.fromDomainModel(bookmark)
    )

    override suspend fun deleteBookmark(bookmark: Bookmark) =
        bookmarkLocalDatasource.deleteBookmark(
            BookmarkEntity.fromDomainModel(bookmark)
        )
}