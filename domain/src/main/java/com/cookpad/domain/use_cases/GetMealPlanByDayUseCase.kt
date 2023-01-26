package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealPlan
import com.cookpad.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealPlanByDayUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(strDayOfWeek: String): Flow<Resource<List<MealPlan>>>{
        return repository.getMealPlansByDayOfTheWeek(strDayOfWeek)
    }
}