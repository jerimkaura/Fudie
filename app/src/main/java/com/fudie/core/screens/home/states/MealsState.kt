package com.fudie.core.screens.home.states

import com.fudie.domain.model.Meal

data class MealsState(
    var isLoading: Boolean = false,
    var data: List<Meal>? = null,
    var error: String = ""
)