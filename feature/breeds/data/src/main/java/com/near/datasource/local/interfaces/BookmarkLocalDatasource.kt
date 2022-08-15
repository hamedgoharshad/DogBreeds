package com.near.datasource.local.interfaces

import com.near.common.data.persistent.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDatasource {
    fun getBookmarks(breed: String?): Flow<List<BookmarkEntity>>
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}