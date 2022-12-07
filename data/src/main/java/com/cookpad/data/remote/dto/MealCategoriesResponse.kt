package com.cookpad.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealCategoriesResponse(
    @Json(name = "meals")
    val categories: List<CategoryDTO>
)