package com.cookpad.domain.model

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val boolIsFavourite: Boolean = false,
)
