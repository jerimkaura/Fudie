package com.fudie.core.screens.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fudie.common.util.Resource
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.utils.Constants
import com.fudie.domain.use_cases.GetMealByCategoryNameUseCase
import com.fudie.domain.use_cases.GetMealByIngredientNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealByIngredientNameUseCase: GetMealByIngredientNameUseCase,
    private val getMealByCategoryNameUseCase: GetMealByCategoryNameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _meals = mutableStateOf(MealsState())
    val meals: State<MealsState> = _meals

    private var _categoryName = mutableStateOf("")
    val categoryName: State<String> = _categoryName

    init {
        savedStateHandle.get<String>(Constants.CATEGORY_NAME)?.let { catName ->
            _categoryName.value = catName
            getMealByCategoryName(catName, _meals)
        }
    }

    private fun getMealByCategoryName(categoryName: String, _state: MutableState<MealsState>) {
        getMealByCategoryNameUseCase(categoryName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}