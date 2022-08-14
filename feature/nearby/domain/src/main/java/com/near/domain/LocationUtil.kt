package com.near.domain

import com.near.domain.model.SimpleLocation

abstract class LocationUtil {
    abstract fun distanceOf(origin: SimpleLocation, dest: SimpleLocation): Float
}