package com.near.common.data.persistent.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.near.common.data.persistent.database.entity.PlaceEntity

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun pagingSource(): PagingSource<Int, PlaceEntity>

    @Query("DELETE FROM place")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PlaceEntity>)
}