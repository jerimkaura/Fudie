package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRandomMeal(): Flow<Resource<Recipe>>
    fun getRecipeByMealId(mealId: String): Flow<Resource<Recipe>>
    suspend fun getAllRecipes(): List<Recipe>
}