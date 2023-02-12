package com.cookpad.core.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.IngredientsState
import com.cookpad.core.screens.home.states.MealCategoriesState
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMealCategoriesUseCase: GetMealCategoriesUseCase,
    private val getRandomRecipeUseCase: GetRandomRecipeUseCase,
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val searchMealUseCase: SearchMealUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase
) :
    ViewModel() {

    private var _ingredients = mutableStateOf(IngredientsState())
    val ingredients: State<IngredientsState> = _ingredients

    private var _mealCategories = mutableStateOf(MealCategoriesState())
    val mealCategories: State<MealCategoriesState> = _mealCategories


    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState


    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    private var _randomRecipe = mutableStateOf(RecipeState())
    val randomRecipe: State<RecipeState> = _randomRecipe

    private var _allMeals: MutableStateFlow<MealsState> = MutableStateFlow(MealsState())
    val allMeals: StateFlow<MealsState> = _allMeals

    init {
        getMealCategories()
        getAllMeals()
    }

    fun getAllMeals() {
        getAllMealsUseCase().onEach { result ->
            _allMeals.value = MealsState(data = result)
        }.launchIn(viewModelScope)
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

    fun searchMeal(searchString: String) {
        searchMealUseCase(searchString).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _allMeals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _allMeals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _allMeals.value = MealsState(error = result.error.toString())
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

    fun toggleFavourite(isFavourite: Boolean, strMealId: String) {
        viewModelScope.launch {
            toggleFavouriteUseCase(isFavourite, strMealId)
        }
    }

}