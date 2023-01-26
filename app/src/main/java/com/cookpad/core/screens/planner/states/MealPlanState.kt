package com.cookpad.core.screens.planner.states

import com.cookpad.domain.model.MealPlan

class MealPlansState(
    var isLoading: Boolean = false,
    var data: List<MealPlan>? = null,
    var error: String = ""
)