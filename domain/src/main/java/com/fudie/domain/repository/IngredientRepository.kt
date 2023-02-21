package com.fudie.domain.repository

import com.fudie.common.util.Resource
import com.fudie.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
     fun getIngredients(): Flow<Resource<List<Ingredient>>>
}