package com.cookpad.domain.use_cases

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealCategory
import com.cookpad.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke() : Flow<Resource<List<MealCategory>>> {
        return repository.getMealCategories()
    }
}