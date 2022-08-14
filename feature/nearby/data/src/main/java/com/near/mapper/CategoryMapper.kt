package com.near.mapper

import com.near.common.domain.mapper.Mapper
import com.near.common.domain.model.Category
import com.near.webApi.response.CategoryResponse
import com.near.webApi.response.iconDimen

object CategoryMapper : Mapper<CategoryResponse, Category> {
    override fun CategoryResponse.map(): Category = Category(icon.prefix + iconDimen+ icon.suffix, id, name)
}