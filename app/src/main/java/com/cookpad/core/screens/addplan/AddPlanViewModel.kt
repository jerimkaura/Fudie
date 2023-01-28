package com.cookpad.core.screens.addplan

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.addplan.states.SearchRecipeState
import com.cookpad.core.screens.addplan.states.SelectedMealsState
import com.cookpad.core.screens.addplan.states.AddPlanState
import com.cookpad.core.screens.utils.getDay
import com.cookpad.domain.model.Meal
import com.cookpad.domain.model.MealPlan
import com.cookpad.domain.use_cases.CreateMealPlanUseCase
import com.cookpad.domain.use_cases.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddPlanViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val createMealPlanUseCase: CreateMealPlanUseCase,
) : ViewModel() {

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    private var _searchRecipeState = MutableStateFlow(SearchRecipeState())
    val searchRecipeState = _searchRecipeState.asStateFlow()

    private var _selectedDay: MutableState<String> = mutableStateOf(value = getDay())
    val selectedDay: State<String> = _selectedDay

    private val _listOfMealsToAddToPlan = mutableStateListOf<Meal>()
    val listOfMealsToAddToPlan: List<Meal> = _listOfMealsToAddToPlan

    private var _selectedMeal = MutableStateFlow(SelectedMealsState())
    val selectedMeal = _selectedMeal.asStateFlow()

    private var _createMealPlanResponse = MutableStateFlow(AddPlanState())
    val createMealPlanResponse = _createMealPlanResponse.asStateFlow()

    fun addToListOfMealsToAddToPlan(meal: Meal) {
        _listOfMealsToAddToPlan.add(meal)
    }

    fun removeFromListOfMealsToAddToPlan(meal: Meal) {
        _listOfMealsToAddToPlan.remove(meal)
    }

    fun cleaMealsList(){
        _listOfMealsToAddToPlan.clear()
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }


    fun updateSelectedDay(newDay: String) {
        _selectedDay.value = newDay
    }

    fun searchMeal(searchString: String) {
        searchRecipeUseCase(searchString).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _searchRecipeState.value = SearchRecipeState(isLoading = true)
                }

                is Resource.Success -> {
                    _searchRecipeState.value = SearchRecipeState(data = result.data, isLoading = false)
                }
                is Resource.Error -> {
                    _searchRecipeState.value = SearchRecipeState(isLoading = false,error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createMealPlan(mealPlans: List<MealPlan>) {
        createMealPlanUseCase.invoke(mealPlans).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _createMealPlanResponse.value = AddPlanState(isLoading = true)
                }

                is Resource.Success -> {
                    _createMealPlanResponse.value = AddPlanState(data = result.data)
                }

                is Resource.Error -> {
                    _createMealPlanResponse.value = AddPlanState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}