package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMealPlanUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(planId:Long):Flow<Resource<String>>{
        return repository.deleteMealPlanById(planId)
    }
}