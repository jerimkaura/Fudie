package com.fudie.data.remote.dto


import com.squareup.moshi.Json

data class MealsResponse(
    @Json(name = "meals")
    val meals: List<MealDTO>
)