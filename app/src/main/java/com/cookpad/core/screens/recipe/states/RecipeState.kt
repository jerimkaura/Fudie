package com.cookpad.core.screens.recipe.states

import com.cookpad.domain.model.Recipe

data class RecipeState(
    var isLoading: Boolean = false,
    var data: Recipe? = null,
    var error: String = ""
)