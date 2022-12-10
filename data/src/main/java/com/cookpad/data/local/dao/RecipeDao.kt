package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: RecipeEntity)

    @Upsert
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipeEntity")
    suspend fun getRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipeEntity WHERE idMeal LIKE '%' || :mealId || '%'")
    suspend fun getRecipeByMealId(mealId: String): RecipeEntity?

    @Query("DELETE FROM recipeEntity WHERE idMeal in (:mealIds)")
    suspend fun deleteRecipes(mealIds: List<String>)

    @Query("DELETE FROM recipeEntity WHERE idMeal LIKE '%' || :mealId || '%'")
    suspend fun deleteRecipeByMealId(mealId: String)
}