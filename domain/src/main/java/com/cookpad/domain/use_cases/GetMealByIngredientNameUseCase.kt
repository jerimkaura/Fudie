package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Meal
import com.cookpad.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealByIngredientNameUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(ingredientName: String): Flow<Resource<List<Meal>>>{
        return repository.getMealByIngredientName(ingredientName)
    }
}