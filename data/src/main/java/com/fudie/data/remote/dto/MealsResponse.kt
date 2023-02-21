package com.fudie.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MealsResponse(
    @Json(name = "meals")
    val meals: List<MealDTO>
)