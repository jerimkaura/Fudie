package com.fudie.core.screens.country

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fudie.common.util.Resource
import com.fudie.core.screens.home.states.CountriesState
import com.fudie.core.screens.home.states.MealsState
import com.fudie.domain.use_cases.GetCountriesUseCase
import com.fudie.domain.use_cases.GetMealByCountryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getMealByCountryNameUseCase: GetMealByCountryNameUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private var _meals = MutableStateFlow(MealsState())
    val meals = _meals.asStateFlow()

    private var _selectedCountryName = mutableStateOf("")
    val selectedCountryName: State<String> = _selectedCountryName

    private var _countries = mutableStateOf(CountriesState())
    val countries: State<CountriesState> = _countries

    init {
        getMealByCountryNameName("American")
        getCountries()
    }

    fun getMealByCountryNameName(categoryName: String) {
        getMealByCountryNameUseCase(categoryName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _meals.value = MealsState(isLoading = true)
                }
                is Resource.Success -> {
                    _meals.value = MealsState(data = result.data)
                }
                is Resource.Error -> {
                    _meals.value = MealsState(error = result.error.toString())
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

                is Resource.Error -> {
                    _countries.value = CountriesState(error = result.error.toString())
                }

                is Resource.Success -> {
                    _countries.value = CountriesState(data =result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}