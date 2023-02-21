package com.fudie.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomMealResponse(
    @Json(name = "meals")
    val meals: List<RecipeDTO>
)