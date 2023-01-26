package com.cookpad.core.screens.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.planner.states.MealPlansState
import com.cookpad.core.screens.utils.getDay
import com.cookpad.domain.use_cases.GetMealPlanByDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val getMealPlanByDayUseCase: GetMealPlanByDayUseCase,
) : ViewModel() {
    private var _mealPlans = MutableStateFlow(MealPlansState())
    val mealPlans = _mealPlans.asStateFlow()

    init {
        getMealPlansByDayOfTheWeek(getDay())
    }


    fun getMealPlansByDayOfTheWeek(strDayOfWeek: String) {
        getMealPlanByDayUseCase(strDayOfWeek).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mealPlans.value = MealPlansState(isLoading = true)
                }

                is Resource.Success -> {
                    _mealPlans.value = MealPlansState(data = result.data)
                }

                is Resource.Error -> {
                    _mealPlans.value = MealPlansState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}