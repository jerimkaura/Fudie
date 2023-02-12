package com.cookpad.core.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.domain.use_cases.GetFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase
) :
    ViewModel() {

    private var _favouritesMeals = MutableStateFlow(MealsState())
    val favouritesMeals = _favouritesMeals.asStateFlow()

    init {
        getFavouriteMeals()
    }

    private fun getFavouriteMeals() {
        getFavouritesUseCase().onEach { result ->
            _favouritesMeals.value = MealsState(data = result)
        }.launchIn(viewModelScope)
    }

}