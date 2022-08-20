package com.hamed.common.data.persistent.database.dao

import androidx.room.*
import com.hamed.common.data.persistent.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getBookmarksByBreed(): Flow<List<BookmarkEntity>> // could be flow

    @Query("SELECT * FROM bookmark WHERE bookmark.breed = :breed")
    fun getBookmarksByBreed(breed:String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(bookmarkEntity: BookmarkEntity)

    @Delete
    fun delete(bookmarkEntity: BookmarkEntity)
}
