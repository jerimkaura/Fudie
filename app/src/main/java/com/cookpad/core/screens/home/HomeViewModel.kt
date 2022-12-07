package com.cookpad.core.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.IngredientsState
import com.cookpad.domain.use_cases.GetIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getIngredientsUseCase: GetIngredientsUseCase) :
    ViewModel() {

    private var _ingredients = mutableStateOf(IngredientsState())
    val ingredients: State<IngredientsState> = _ingredients

    init {
        getIngredients()
    }

    private fun getIngredients() {
        getIngredientsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _ingredients.value = IngredientsState(isLoading = true)
                }
                is Resource.Success -> {
                    _ingredients.value = IngredientsState(data = result.data)
                }
                is Resource.Error -> {
                    _ingredients.value = IngredientsState(error = result.error.toString())
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}