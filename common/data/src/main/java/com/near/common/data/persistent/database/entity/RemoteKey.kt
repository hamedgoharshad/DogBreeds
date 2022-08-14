package com.near.common.data.persistent.database.entity

import androidx.room.Entity

@Entity(tableName = "remote_key", primaryKeys = ["latLong"])
data class RemoteKey(
    val latLong: String,
    val cursor: String,
    val limit: Int,
    val query: String
)

