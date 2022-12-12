package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Meal
import com.cookpad.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealByCategoryNameUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(categoryName: String): Flow<Resource<List<Meal>>>{
        return repository.getMealByCategoryName(categoryName)
    }
}