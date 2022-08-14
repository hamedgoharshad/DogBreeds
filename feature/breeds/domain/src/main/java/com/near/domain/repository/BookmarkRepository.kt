package com.near.domain.repository

import com.near.common.domain.model.Bookmark

interface BookmarkRepository {
    suspend fun getBookmarks(): List<Bookmark>
    suspend fun addBookmark(bookmark: Bookmark)
    suspend fun deleteBookmark(bookmark: Bookmark)
}
