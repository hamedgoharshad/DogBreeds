package com.hamed.common.testshared

import com.hamed.common.data.persistent.database.entity.BookmarkEntity

class TestData {

    companion object {
        const val nearDistance = 80F
        const val awayDistance = 120F
    }

    val fakePlaceDetailEntity =
        BookmarkEntity("fakeId", "fakeName")

}