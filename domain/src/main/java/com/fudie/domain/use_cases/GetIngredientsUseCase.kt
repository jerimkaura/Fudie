package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.Ingredient
import com.fudie.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(private val repository: IngredientRepository) {
    operator fun invoke(): Flow<Resource<List<Ingredient>>> {
        return repository.getIngredients()
    }
}