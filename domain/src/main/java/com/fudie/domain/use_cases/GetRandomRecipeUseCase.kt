package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.Recipe
import com.fudie.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(): Flow<Resource<Recipe>>{
        return repository.getRandomMeal()
    }
}