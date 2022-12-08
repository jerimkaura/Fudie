package com.cookpad.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class IngredientsResponse(
    @Json(name = "meals")
    val meals: List<IngredientDTO>
)