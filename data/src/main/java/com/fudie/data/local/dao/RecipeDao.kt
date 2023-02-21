package com.fudie.data.local.dao

import androidx.room.*
import com.fudie.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertRecipe(recipe: RecipeEntity)

    @Upsert
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipeEntity")
    suspend fun getRecipes(): List<RecipeEntity>?

    @Query("SELECT * FROM recipeEntity WHERE idMeal LIKE '%' || :mealId || '%'")
    suspend fun getRecipeByMealId(mealId: String): RecipeEntity?

    @Query("SELECT * FROM recipeEntity WHERE strMeal LIKE '%' || :searchString || '%'")
    suspend fun searchRecipe(searchString: String): List<RecipeEntity>
}