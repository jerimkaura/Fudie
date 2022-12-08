package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cookpad.data.local.entity.MealCategoryEntity

@Dao
interface MealCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealCategories(mealCategories: List<MealCategoryEntity>)

    @Query("DELETE FROM mealCategoryEntity WHERE strCategory in(:mealCategoriesNames)")
    suspend fun deleteMealCategories(mealCategoriesNames: List<String>)

    @Query("SELECT * FROM mealCategoryEntity")
    suspend fun getMealCategories(): List<MealCategoryEntity>
}