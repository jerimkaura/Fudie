package com.cookpad.core.screens.planner.states

data class DeletePlanState(
    var isLoading: Boolean = false,
    var data: String? = null,
    var error: String = ""
)
