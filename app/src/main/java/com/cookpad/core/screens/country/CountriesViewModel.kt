package com.cookpad.core.screens.country

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.common.util.Resource
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.domain.use_cases.GetCountriesUseCase
import com.cookpad.domain.use_cases.GetMealByCountryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getMealByCountryNameUseCase: GetMealByCountryNameUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private var _meals = mutableStateOf(MealsState())
    val meals: State<MealsState> = _meals


    init {
        getMealByCountryNameName("American")
    }

    fun getMealByCountryNameName(categoryName: String) {
        getMealByCountryNameUseCase(categoryName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _meals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _meals.value = MealsState(data = result.data)
                    Log.d(categoryName.uppercase(), "getMealByCountryNameName: ${result.data}")
                }
                is Resource.Error -> {
                    _meals.value = MealsState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}