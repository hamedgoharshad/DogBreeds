package com.near.mapper

import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.common.domain.mapper.Mapper
import com.near.webApi.response.NearbyResponse
import com.near.webApi.response.iconDimen

object NearbyResponseMapper :
    Mapper<NearbyResponse, List<PlaceEntity>> {
    override fun NearbyResponse.map(): List<PlaceEntity> =
        results.map {
            PlaceEntity(
                it.fsq_id,
                it.categories.first()?.name ?:"",
                it.name,
                it.categories.first()?.icon?.run { prefix.plus(iconDimen.plus(suffix)) } ?: "")
        }
}