package com.near.utils

import com.near.mapper.mapToLocation
import javax.inject.Inject

class LocationUtilsImpl @Inject constructor() : LocationUtil() {
    override fun distanceOf(origin: SimpleLocation, dest: SimpleLocation): Float =
        origin.mapToLocation().distanceTo(dest.mapToLocation())
}