package com.near.common.data.persistent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.near.common.data.persistent.database.dao.BookmarkDao
import com.near.common.data.persistent.database.entity.BookmarkEntity

@Database(
    entities = [
        BookmarkEntity::class
    ],
    version =1,
    exportSchema = true
)
abstract class NearbyDatabase : RoomDatabase() {
    abstract fun getBookmarkDao(): BookmarkDao
}