package com.cookpad.core.screens.addplan.states

import com.cookpad.domain.model.MealPlan

data class AddPlanState(
    var isLoading: Boolean = false,
    var data: String? = null,
    var error: String = ""
)