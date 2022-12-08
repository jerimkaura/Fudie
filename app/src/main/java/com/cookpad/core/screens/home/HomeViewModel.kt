package com.cookpad.core.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.CountriesState
import com.cookpad.core.screens.home.states.IngredientsState
import com.cookpad.core.screens.home.states.MealCategoriesState
import com.cookpad.domain.use_cases.GetCountriesUseCase
import com.cookpad.domain.use_cases.GetIngredientsUseCase
import com.cookpad.domain.use_cases.GetMealCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getMealCategoriesUseCase: GetMealCategoriesUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) :
    ViewModel() {

    private var _ingredients = mutableStateOf(IngredientsState())
    val ingredients: State<IngredientsState> = _ingredients

    private var _mealCategories = mutableStateOf(MealCategoriesState())
    val mealCategories: State<MealCategoriesState> = _mealCategories

    private var _countries = mutableStateOf(CountriesState())
    val countries: State<CountriesState> = _countries

    init {
        getMealCategories()
        getIngredients()
        getCountries()
    }

    private fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _countries.value = CountriesState(isLoading = true)
                }
                is Resource.Success -> {
                    _countries.value = CountriesState(data = result.data)
                }
                is Resource.Error -> {
                    _countries.value = CountriesState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getMealCategories() {
        getMealCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mealCategories.value = MealCategoriesState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealCategories.value = MealCategoriesState(data = result.data)
                }
                is Resource.Error -> {
                    _mealCategories.value = MealCategoriesState(error = result.error.toString())
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
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