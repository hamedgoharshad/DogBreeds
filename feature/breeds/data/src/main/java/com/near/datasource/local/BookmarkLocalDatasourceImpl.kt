package com.near.datasource.local

import com.near.common.data.persistent.database.dao.BookmarkDao
import com.near.common.data.persistent.database.entity.BookmarkEntity
import com.near.datasource.local.interfaces.BookmarkLocalDatasource
import javax.inject.Inject

class BookmarkLocalDatasourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkLocalDatasource {

    override suspend fun getBookmarks(): List<BookmarkEntity> =
        bookmarkDao.getAllBookmarks()

    override suspend fun addBookmark(bookmarkEntity: BookmarkEntity) =
        bookmarkDao.add(bookmarkEntity)

    override suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity) =
        bookmarkDao.delete(bookmarkEntity)
}