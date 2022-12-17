package com.cookpad.core.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.CountriesState
import com.cookpad.core.screens.home.states.IngredientsState
import com.cookpad.core.screens.home.states.MealCategoriesState
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.domain.model.Country
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
    private val getMealByCategoryNameUseCase: GetMealByCategoryNameUseCase,
    private val getMealByIngredientNameUseCase: GetMealByIngredientNameUseCase,
    private val getRandomRecipeUseCase: GetRandomRecipeUseCase,
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

    private var _randomRecipe = mutableStateOf(RecipeState())
    val randomRecipe: State<RecipeState> = _randomRecipe

    init {
        getMealCategories()
        getIngredients()
        getCountries()
        getMealByCategoryName("Chicken", _chickenMeals)
        getMealByCategoryName("Beef", _beefMeals)
        getMealByCategoryName("Pork", _porkMeals)
        getMealByCategoryName("Vegetarian", _vegetarianMeals)
        getMealByIngredientName("Milk", _breakfastMeals)
    }


    fun getRandomRecipe() {
        getRandomRecipeUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _randomRecipe.value = RecipeState(isLoading = true)
                }
                is Resource.Success -> {
                    _randomRecipe.value = RecipeState(data = result.data)
                }
                is Resource.Error -> {
                    _randomRecipe.value = RecipeState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
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

    private fun getMealByIngredientName(ingredientName: String, _state: MutableState<MealsState>) {
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