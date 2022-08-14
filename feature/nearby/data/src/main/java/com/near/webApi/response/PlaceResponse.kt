package com.near.webApi.response

import com.near.common.domain.model.Geocodes

data class PlaceResponse(
    val categories: List<CategoryResponse?>,
    val chains: List<Any>,
    val distance: Int,
    val fsq_id: String,
    val geocodes: Geocodes,
    val location: LocationResponse,
    val name: String,
    val related_places: RelatedPlaces,
    val timezone: String
)