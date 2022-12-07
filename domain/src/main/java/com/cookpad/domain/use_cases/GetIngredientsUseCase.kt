package com.cookpad.domain.use_cases

import android.util.Log
import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Ingredient
import com.cookpad.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(private val repository: IngredientsRepository) {
    operator fun  invoke(): Flow<Resource<List<Ingredient>>> {
        Log.d("INGREDIENTS", "invoke: ${repository.getIngredients()} ")
        return repository.getIngredients()

    }
}