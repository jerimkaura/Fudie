package com.cookpad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.domain.model.Meal

@Entity
data class MealEntity(
    @PrimaryKey
    val id: Int? = null,
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String = "default"
) {
    fun toDomain(): Meal {
        return Meal(
            idMeal = idMeal,
            strMeal = strMeal,
            strMealThumb = strMealThumb
        )
    }
}