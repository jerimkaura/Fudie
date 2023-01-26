package com.cookpad.core.screens.addplan.states

import com.cookpad.domain.model.Recipe

data class SearchRecipeState(
    var isLoading: Boolean = false,
    var data: List<Recipe>? = null,
    var error: String = ""
)
