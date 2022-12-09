package com.cookpad.core.screens.home.states

import com.cookpad.domain.model.Meal

data class MealsState(
    var isLoading: Boolean = false,
    var data: List<Meal>? = null,
    var error: String = ""
)