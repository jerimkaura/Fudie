package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealPlan
import kotlinx.coroutines.flow.Flow

interface MealPlanRepository {
    fun createMealPlan(mealPlans: List<MealPlan>): Flow<Resource<String>>
    fun getMealPlans(): Flow<Resource<List<MealPlan>>>
    fun getMealPlansByDayOfTheWeek(strDayOfWeek: String): Flow<Resource<List<MealPlan>>>
}