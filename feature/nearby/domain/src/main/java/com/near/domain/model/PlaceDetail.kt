package com.near.domain.model

import com.near.common.domain.model.Geocodes

data class PlaceDetail(
    val fsqId: String,
    val name: String,
    val categoryName: String,
    val geocodes: Geocodes,
    val timezone: String,
    val address :String,
    val icon: String
)
