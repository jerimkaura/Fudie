package com.fudie.data.remote.dto


import com.squareup.moshi.Json

data class MealCategoriesResponse(
    @Json(name = "categories")
    val categories: List<CategoryDTO>?
)