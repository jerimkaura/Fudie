package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.MealPlan
import com.fudie.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateMealPlanUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(mealPlans: List<MealPlan>): Flow<Resource<String>>{
        return repository.createMealPlan(mealPlans)
    }
}