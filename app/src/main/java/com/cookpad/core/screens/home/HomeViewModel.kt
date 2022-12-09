package com.cookpad.core.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.home.states.CountriesState
import com.cookpad.core.screens.home.states.IngredientsState
import com.cookpad.core.screens.home.states.MealCategoriesState
import com.cookpad.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getMealCategoriesUseCase: GetMealCategoriesUseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getChickenMealsUseCase: GetChickenMealsUseCase,
    private val getPorkMealsUseCase: GetPorkMealsUseCase,
    private val getBeefMealsUseCase: GetBeefMealsUseCase,
    private val getVegetarianMealsUseCase: GetVegetarianMealsUseCase,
    private val getBreakfastMealsUseCase: GetBreakfastMealsUseCase,
) :
    ViewModel() {

    private var _ingredients = mutableStateOf(IngredientsState())
    val ingredients: State<IngredientsState> = _ingredients

    private var _mealCategories = mutableStateOf(MealCategoriesState())
    val mealCategories: State<MealCategoriesState> = _mealCategories

    private var _countries = mutableStateOf(CountriesState())
    val countries: State<CountriesState> = _countries

    private var _chickenMeals = mutableStateOf(MealsState())
    val chickenMeals: State<MealsState> = _chickenMeals

    private var _beefMeals = mutableStateOf(MealsState())
    val beefMeals: State<MealsState> = _beefMeals

    private var _porkMeals = mutableStateOf(MealsState())
    val porkMeals: State<MealsState> = _porkMeals

    private var _vegetarianMeals = mutableStateOf(MealsState())
    val vegetarianMeals: State<MealsState> = _vegetarianMeals

    private var _breakfastMeals = mutableStateOf(MealsState())
    val breakfastMeals: State<MealsState> = _breakfastMeals


    init {
        getMealCategories()
        getIngredients()
        getCountries()
        getChickenMeals()
        getBeefMeals()
        getPorkMeals()
        getVegetarianMeals()
        getBreakFastMeals()
    }

    private fun getBreakFastMeals() {
        getBreakfastMealsUseCase().onEach { result ->
            when (result){
                is Resource.Loading -> {
                    _breakfastMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _breakfastMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _breakfastMeals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getVegetarianMeals() {
        getVegetarianMealsUseCase().onEach { result ->
            when (result){
                is Resource.Loading -> {
                    _vegetarianMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _vegetarianMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _vegetarianMeals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getPorkMeals() {
        getPorkMealsUseCase().onEach { result ->
            when (result){
                is Resource.Loading -> {
                    _porkMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _porkMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _porkMeals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getBeefMeals() {
        getBeefMealsUseCase().onEach { result ->
            when (result){
                is Resource.Loading -> {
                    _beefMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _beefMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _beefMeals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getChickenMeals() {
        getChickenMealsUseCase().onEach { result ->
            when (result){
                is Resource.Loading -> {
                    _chickenMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _chickenMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _chickenMeals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
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