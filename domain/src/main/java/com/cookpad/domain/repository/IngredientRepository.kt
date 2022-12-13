package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {
     fun getIngredients(): Flow<Resource<List<Ingredient>>>
}