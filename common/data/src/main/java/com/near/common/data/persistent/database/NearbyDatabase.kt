package com.near.common.data.persistent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.near.common.data.persistent.database.dao.PlaceDao
import com.near.common.data.persistent.database.dao.PlaceDetailDao
import com.near.common.data.persistent.database.dao.RemoteKeyDao
import com.near.common.data.persistent.database.entity.PlaceDetailEntity
import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.common.data.persistent.database.entity.RemoteKey

@Database(
    entities = [
        PlaceEntity::class, RemoteKey::class,PlaceDetailEntity::class
    ],
    version =1,
    exportSchema = true
)
abstract class NearbyDatabase : RoomDatabase() {
    abstract fun getPlaceDao(): PlaceDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao
    abstract fun getPlaceDetailDao(): PlaceDetailDao
}