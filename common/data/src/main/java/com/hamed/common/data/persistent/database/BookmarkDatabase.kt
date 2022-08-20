package com.hamed.common.data.persistent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamed.common.data.persistent.database.dao.BookmarkDao
import com.hamed.common.data.persistent.database.entity.BookmarkEntity

@Database(
    entities = [
        BookmarkEntity::class
    ],
    version =1,
    exportSchema = true
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun getBookmarkDao(): BookmarkDao
}