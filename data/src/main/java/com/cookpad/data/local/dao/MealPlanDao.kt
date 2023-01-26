package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.cookpad.data.local.entity.MealPlanEntity

@Dao
interface MealPlanDao {
    @Upsert
    suspend fun insertMealPlans(mealPlanEntities: List<MealPlanEntity>)

    @Query("SELECT * FROM mealPlanEntity")
    suspend fun getMealPlans(): List<MealPlanEntity>

    @Query("SELECT * FROM mealPlanEntity WHERE strDayOfWeek  LIKE '%' || :strDayOfWeek || '%'")
    suspend fun getMealPlanByDayOfTheWeek(strDayOfWeek: String): List<MealPlanEntity>
}