package com.cookpad.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class IngredientsResponse(
    @Json(name = "meals")
    val meals: List<IngredientDTO>
)