package com.near.common.testshared

import com.near.common.data.persistent.database.entity.PlaceDetailEntity

class TestData {

    companion object {
        const val nearDistance = 80F
        const val awayDistance = 120F
    }

    val fakePlaceDetailEntity =
        PlaceDetailEntity("fakeId", "fakeName", "catName", "12", "13", "iran", "mashhad", "ic.jpg")

}