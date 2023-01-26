package com.cookpad.core.screens.planner.states

import com.cookpad.domain.model.MealPlan

data class AddPlanState(
    var isLoading: Boolean = false,
    var data: String? = null,
    var error: String = ""
)