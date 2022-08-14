package com.near.common.data.persistent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "category_name")
    var categoryName: String,
    @ColumnInfo(name = "location_name")
    var locationName: String,
    @ColumnInfo(name = "icon")
    var icon: String
)
