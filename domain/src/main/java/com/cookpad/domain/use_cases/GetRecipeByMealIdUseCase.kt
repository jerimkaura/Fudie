package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Recipe
import com.cookpad.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeByMealIdUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(mealId: String): Flow<Resource<Recipe>> {
        return repository.getRecipeByMealId(mealId)
    }
}