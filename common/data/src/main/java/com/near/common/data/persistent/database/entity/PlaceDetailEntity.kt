package com.near.common.data.persistent.database.entity

import androidx.room.Entity

@Entity(
    tableName = "place_detail",
    primaryKeys = ["id"]
)
data class PlaceDetailEntity(
    val id: String,
    val name: String,
    val categoryName: String,
    val latitude: String,
    val longitude: String,
    val timezone: String,
    val address: String,
    val icon: String,
)
