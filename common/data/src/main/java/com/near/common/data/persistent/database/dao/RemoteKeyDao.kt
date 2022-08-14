package com.near.common.data.persistent.database.dao

import androidx.room.*
import com.near.common.data.persistent.database.entity.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Transaction
    @Query("SELECT * FROM remote_key WHERE latLong = :latLong")
    suspend fun getByQuery(latLong: String): RemoteKey

    @Query("DELETE FROM remote_key WHERE latLong = :latLong")
    suspend fun deleteByQuery(latLong: String)
}
