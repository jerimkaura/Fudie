package com.fudie.domain.repository

import com.fudie.common.util.Resource
import com.fudie.domain.model.Meal
import com.fudie.domain.model.MealCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getMealCategories(): Flow<Resource<List<MealCategory>>>
    suspend fun getMealsByCategories()
}