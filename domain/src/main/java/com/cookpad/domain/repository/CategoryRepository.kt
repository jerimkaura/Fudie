package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.MealCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getMealCategories(): Flow<Resource<List<MealCategory>>>
}