package com.hamed.data.local

import com.hamed.common.data.persistent.database.dao.BookmarkDao
import com.hamed.common.data.persistent.database.entity.BookmarkEntity
import com.hamed.data.local.interfaces.BookmarkLocalDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BookmarkLocalDatasourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkLocalDatasource {

    override fun getBookmarks(breed: String?): Flow<List<BookmarkEntity>> =
        if (breed.isNullOrBlank()) bookmarkDao.getBookmarksByBreed() else bookmarkDao.getBookmarksByBreed(breed)

    override suspend fun addBookmark(bookmarkEntity: BookmarkEntity) =
        bookmarkDao.add(bookmarkEntity)

    override suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity) =
        bookmarkDao.delete(bookmarkEntity)
}