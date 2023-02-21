package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.Recipe
import com.fudie.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(searString: String): Flow<Resource<List<Recipe>>>{
        return repository.searchRecipe(searString)
    }
}