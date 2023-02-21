package com.fudie.core.screens.home.states

import com.fudie.domain.model.MealCategory

data class MealCategoriesState(
    var isLoading: Boolean = false,
    var data: List<MealCategory>? = null,
    var error: String = ""
)