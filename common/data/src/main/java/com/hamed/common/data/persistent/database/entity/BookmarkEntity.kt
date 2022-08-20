package com.hamed.common.data.persistent.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamed.common.domain.model.Bookmark

@Entity(
    tableName = "bookmark",
)
data class BookmarkEntity(
    @PrimaryKey
    val id: String,
    val breed: String,
) {
    fun toDomainModel() = Bookmark(id, breed)

    companion object {
        fun fromDomainModel(bookmark: Bookmark) = BookmarkEntity(bookmark.id, bookmark.breed)
    }
}
