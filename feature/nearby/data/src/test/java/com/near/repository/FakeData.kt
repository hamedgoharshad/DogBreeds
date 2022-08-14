package com.near.repository

import android.location.Location
import com.near.common.domain.model.Geocodes
import com.near.common.domain.model.MainGeo
import com.near.domain.model.SimpleLocation
import com.near.webApi.response.*
import retrofit2.Response

class FakeData {
    companion object {
        val fakePlaceDetailResponse: Response<PlaceDetailResponse> = Response.success(
            PlaceDetailResponse(
                listOf(
                    CategoryResponse(
                        1,
                        "cat1",
                        Icon(
                            "ic1",
                            "jpg"
                        )
                    )
                ),
                listOf(),
                "id1",
                Geocodes(
                    MainGeo(
                        30.0, 60.0
                    )
                ),
                LocationResponse(
                    "",
                    "",
                    ""
                ),
                "name",
                RelatedPlaces(),
                "tehran"
            )
        )
    }
}

val simpleLocationFixtures = SimpleLocation(1.1, 1.1)
val locationFixtures = Location("").apply {
    latitude = 55.35
    longitude = 12.96
}
val exceptionFixtures = Exception(Throwable("fake exception"))
