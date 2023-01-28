package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cookpad.data.local.entity.MealPlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealPlanDao {
    @Upsert
    suspend fun insertMealPlans(mealPlanEntities: List<MealPlanEntity>)

    @Query("SELECT * FROM mealPlanEntity")
    fun getMealPlans(): Flow<List<MealPlanEntity>>

    @Query("SELECT * FROM mealPlanEntity WHERE strDayOfWeek  LIKE '%' || :strDayOfWeek || '%'")
    fun getMealPlanByDayOfTheWeek(strDayOfWeek: String): Flow<List<MealPlanEntity>>

    @Query("DELETE FROM mealPlanEntity WHERE longId = :planId")
    suspend fun deleteMealPlanById(planId: Long)
}