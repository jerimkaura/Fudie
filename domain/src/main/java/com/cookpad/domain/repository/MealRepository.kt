package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository  {
    fun getMealByCategoryName(categoryName: String): Flow<Resource<List<Meal>>>
    fun getMealByIngredientName(ingredientName: String): Flow<Resource<List<Meal>>>
}