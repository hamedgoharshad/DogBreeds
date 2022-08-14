package com.near.common.data.persistent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.near.common.data.persistent.database.entity.PlaceDetailEntity

@Dao
interface PlaceDetailDao {

    @Query("SELECT * FROM place_detail WHERE id=(:id) ")
    fun get(id: String): PlaceDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(placeDetailEntity: PlaceDetailEntity)

}
