package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.MealCategory
import com.fudie.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke() : Flow<Resource<List<MealCategory>>> {
        return repository.getMealCategories()
    }
}