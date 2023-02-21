package com.fudie.domain.use_cases

import com.fudie.domain.model.MealPlan
import com.fudie.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealPlanByDayUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(strDayOfWeek: String): Flow<List<MealPlan>> {
        return repository.getMealPlansByDayOfTheWeek(strDayOfWeek)
    }
}