package com.fudie.core.screens.addplan.states

data class AddPlanState(
    var isLoading: Boolean = false,
    var data: String? = null,
    var error: String = ""
)