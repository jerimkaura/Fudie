package com.cookpad.core.screens.recipe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.core.screens.utils.Constants.MEAL_ID
import com.cookpad.domain.use_cases.GetRecipeByMealIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeViewModels @Inject constructor(
    private val getRecipeByMealIdUseCase: GetRecipeByMealIdUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var _recipe = mutableStateOf(RecipeState())
    val recipe: State<RecipeState> = _recipe


    init {
        savedStateHandle.get<String>(MEAL_ID)?.let { mealId ->
            getRecipeByMealId(mealId)
        }
    }

    private fun getRecipeByMealId(mealId: String) {
        getRecipeByMealIdUseCase(mealId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _recipe.value = RecipeState(isLoading = true)
                }
                is Resource.Success -> {
                    _recipe.value = RecipeState(data = result.data)
                }
                is Resource.Error -> {
                    _recipe.value = RecipeState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
