package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.MealCategoryEntity

@Dao
interface MealCategoryDao {
    @Upsert
    suspend fun upsertMeals(categories: List<MealCategoryEntity>)

    @Query("SELECT * FROM mealCategoryEntity")
    suspend fun getMealCategories(): List<MealCategoryEntity>
}