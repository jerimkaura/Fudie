package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cookpad.data.local.entity.MealEntity
import com.cookpad.domain.model.Ingredient

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(mealS: List<MealEntity>)

    @Query("SELECT * FROM mealEntity")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strMeal  LIKE '%' || :mealName || '%'")
    suspend fun getMealsByName(mealName: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCategory  LIKE '%' || :mealCategory || '%'")
    suspend fun getMealsByCategoryName(mealCategory: String): List<MealEntity>

    @Query("DELETE FROM mealEntity WHERE strMeal in (:meals)")
    suspend fun deleteMeals(meals: List<String>)
}