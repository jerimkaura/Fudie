package com.fudie.core.screens.ingredient

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.fudie.common.util.Resource
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.utils.Constants
import com.fudie.domain.use_cases.GetMealByIngredientNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    private val getMealByIngredientNameUseCase: GetMealByIngredientNameUseCase, savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private  var _ingredientName = mutableStateOf("")
    val ingredientName : State<String> = _ingredientName

    private var _meals = MutableStateFlow(MealsState())
    val meals = _meals.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.INGREDIENT_NAME)?.let { ingredientName ->
            _ingredientName.value = ingredientName
            getMealByIngredientName(ingredientName, _meals)
        }
    }

    private fun getMealByIngredientName(ingredientName: String, _state: MutableStateFlow<MealsState>) {
        getMealByIngredientNameUseCase(ingredientName).onEach { result ->
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