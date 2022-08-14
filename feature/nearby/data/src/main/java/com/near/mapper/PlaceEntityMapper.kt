package com.near.mapper

import com.near.common.data.persistent.database.entity.PlaceEntity
import com.near.common.domain.mapper.Mapper
import com.near.domain.model.Place

object PlaceEntityMapper : Mapper<PlaceEntity, Place> {
    override fun PlaceEntity.map(): Place =
        Place(id, categoryName, locationName, icon)
}