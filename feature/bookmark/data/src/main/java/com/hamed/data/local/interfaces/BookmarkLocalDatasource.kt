package com.hamed.data.local.interfaces

import com.hamed.common.data.persistent.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

internal interface BookmarkLocalDatasource {
    fun getBookmarks(breed: String?): Flow<List<BookmarkEntity>>
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}