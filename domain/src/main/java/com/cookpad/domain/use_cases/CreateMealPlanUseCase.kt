package com.cookpad.domain.use_cases

import android.util.Log
import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealPlan
import com.cookpad.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateMealPlanUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(mealPlans: List<MealPlan>): Flow<Resource<String>>{
        return repository.createMealPlan(mealPlans)
    }
}