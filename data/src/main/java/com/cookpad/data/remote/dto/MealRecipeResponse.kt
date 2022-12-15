package com.cookpad.data.remote.dto


import com.squareup.moshi.Json

data class MealRecipeResponse(
    @Json(name = "meals")
    val meals: List<RecipeDTO>?
)