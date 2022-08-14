package com.near.mapper

import android.location.Location

fun Location.mapToSimpleLocation(): SimpleLocation =
    SimpleLocation(latitude, longitude)

fun SimpleLocation.mapToLocation(): Location =
    Location("").apply {
        latitude = lat
        longitude = long
    }

fun Location.mapToString(): String =
    latitude.toString() + LOCATION_LAT_LONG_SEP + longitude

fun SimpleLocation.mapToString(): String =
    lat.toString() + LOCATION_LAT_LONG_SEP + long

fun String.mapToLocation(): Location =
    split(LOCATION_LAT_LONG_SEP).let { split ->
        Location("location").apply {
            latitude = split[0].toDouble()
            longitude = split[1].toDouble()
        }
    }

fun String.mapToSimpleLocation(): SimpleLocation =
    split(LOCATION_LAT_LONG_SEP).let { split ->
        SimpleLocation(
            split[0].toDouble(),
            split[1].toDouble()
        )
    }

private const val LOCATION_LAT_LONG_SEP = ","