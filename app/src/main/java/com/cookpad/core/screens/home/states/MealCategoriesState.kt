package com.cookpad.core.screens.home.states

import com.cookpad.domain.model.MealCategory

data class MealCategoriesState(
    var isLoading: Boolean = false,
    var data: List<MealCategory>? = null,
    var error: String = ""
)