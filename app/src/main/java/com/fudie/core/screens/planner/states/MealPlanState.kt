package com.fudie.core.screens.planner.states

import com.fudie.domain.model.MealPlan

class MealPlansState(
    var isLoading: Boolean = false,
    var data: List<MealPlan>? = null,
    var error: String = ""
)