package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Upsert
    suspend fun upsertMeals(meals: List<MealEntity>)

    @Query("SELECT * FROM mealEntity")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strMeal  LIKE '%' || :mealName || '%'")
    suspend fun getMealsByName(mealName: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCategory  LIKE '%' || :mealCategory || '%'")
    suspend fun getMealsByCategoryName(mealCategory: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCountry LIKE '%' || :countryName || '%' ")
    suspend fun getMealsByCountryName(countryName: String): List<MealEntity>
}