package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealCategory

interface MealCategoryRepository {
    fun getMealCategories(): kotlinx.coroutines.flow.Flow<Resource<List<MealCategory>>>
}