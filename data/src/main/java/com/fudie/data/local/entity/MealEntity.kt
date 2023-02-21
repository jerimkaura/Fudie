package com.fudie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fudie.domain.model.Meal

@Entity
data class MealEntity(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String? = "default",
    val strCountry: String? = "Not Available",
    val boolIsFavourite: Boolean = false
) {
    fun toDomain(): Meal {
        return Meal(
            idMeal = idMeal,
            strMeal = strMeal,
            strMealThumb = strMealThumb,
            boolIsFavourite = boolIsFavourite
        )
    }
}

