package com.cookpad.core.screens.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.utils.Constants
import com.cookpad.domain.use_cases.GetMealByCategoryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealByCategoryNameUseCase: GetMealByCategoryNameUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _meals = mutableStateOf(MealsState())
    val meals: State<MealsState> = _meals

    init {
        savedStateHandle.get<String>(Constants.CATEGORY_NAME)?.let { categoryName ->
            getMealByCategoryName(categoryName, _meals)
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