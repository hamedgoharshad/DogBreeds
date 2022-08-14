package com.near.mapper

import com.near.common.data.persistent.database.entity.PlaceDetailEntity
import com.near.common.domain.mapper.Mapper
import com.near.mapper.CategoryMapper.map
import com.near.webApi.response.PlaceDetailResponse
import com.near.webApi.response.iconDimen

object PlaceDetailResponseMapper : Mapper<PlaceDetailResponse, PlaceDetailEntity> {
    override fun PlaceDetailResponse.map() = PlaceDetailEntity(
        fsq_id,
        name,
        categories.first()?.map()?.name ?: String(),
        geocodes.main.latitude.toString(),
        geocodes.main.longitude.toString(),
        timezone,
        location.country + location.cross_street + location.postcode,
        categories[0]?.icon?.prefix + iconDimen + categories[0]?.icon?.suffix
    )
}
