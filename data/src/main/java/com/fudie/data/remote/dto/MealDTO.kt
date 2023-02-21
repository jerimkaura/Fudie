package com.fudie.data.remote.dto


import com.fudie.data.local.entity.MealEntity
import com.squareup.moshi.Json

data class MealDTO(
    @Json(name = "idMeal")
    val idMeal: String,
    @Json(name = "strMeal")
    val strMeal: String,
    @Json(name = "strMealThumb")
    val strMealThumb: String
){
    fun toMealEntity(): MealEntity {
        return MealEntity(
            idMeal = idMeal,
            strMeal = strMeal,
            strMealThumb = strMealThumb
        )
    }
}