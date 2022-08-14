package com.near.common.data.persistent.database.dao

import androidx.room.*
import com.near.common.data.persistent.database.entity.BookmarkEntity

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): List<BookmarkEntity> // could be flow

    @Query("SELECT * FROM bookmark WHERE bookmark.breed = :breed")
    fun getAllBookmarks(breed:String): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(bookmarkEntity: BookmarkEntity)

    @Delete
    fun delete(bookmarkEntity: BookmarkEntity)
}
