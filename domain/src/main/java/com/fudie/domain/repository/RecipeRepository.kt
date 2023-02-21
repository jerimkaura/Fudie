package com.fudie.domain.repository

import com.fudie.common.util.Resource
import com.fudie.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRandomMeal(): Flow<Resource<Recipe>>
    fun getRecipeByMealId(mealId: String): Flow<Resource<Recipe>>
    suspend fun getAllRecipes(): List<Recipe>
    fun searchRecipe(searchString: String): Flow<Resource<List<Recipe>>>
}