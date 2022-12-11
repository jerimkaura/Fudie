package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.MealEntity
import com.cookpad.domain.model.Ingredient

@Dao
interface MealDao {

    @Upsert
    suspend fun insertMeals(meals: List<MealEntity>)

    @Query("SELECT * FROM mealEntity")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strMeal  LIKE '%' || :mealName || '%'")
    suspend fun getMealsByName(mealName: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCategory  LIKE '%' || :mealCategory || '%'")
    suspend fun getMealsByCategoryName(mealCategory: String): List<MealEntity>

    @Query("DELETE FROM mealEntity WHERE strMeal in (:meals)")
    suspend fun deleteMeals(meals: List<String>)
}