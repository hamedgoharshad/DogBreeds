package com.near.datasource.local.interfaces

import com.near.common.data.persistent.database.entity.BookmarkEntity

interface BookmarkLocalDatasource {
    suspend fun getBookmarks(): List<BookmarkEntity>
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}