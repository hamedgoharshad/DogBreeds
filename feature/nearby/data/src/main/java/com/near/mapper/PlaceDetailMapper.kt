package com.near.mapper

import com.near.common.data.persistent.database.entity.PlaceDetailEntity
import com.near.common.domain.mapper.Mapper
import com.near.common.domain.model.Geocodes
import com.near.common.domain.model.MainGeo
import com.near.domain.model.PlaceDetail

object PlaceDetailMapper : Mapper<PlaceDetailEntity, PlaceDetail> {
    override fun PlaceDetailEntity.map() = PlaceDetail(
        id,
        name,
        categoryName,
        Geocodes(MainGeo(latitude.toDouble(), longitude.toDouble())),
        timezone,
        address,
        icon
    )
}

