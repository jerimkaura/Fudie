package com.fudie.data.local.dao

import androidx.room.*
import com.fudie.data.local.entity.MealCategoryEntity

@Dao
interface MealCategoryDao {
    @Upsert
    suspend fun upsertMeals(categories: List<MealCategoryEntity>)

    @Query("SELECT * FROM mealCategoryEntity")
    suspend fun getMealCategories(): List<MealCategoryEntity>
}