package com.fudie.core.screens.recipe.states

import com.fudie.domain.model.Recipe

data class RecipeState(
    var isLoading: Boolean = false,
    var data: Recipe? = null,
    var error: String = ""
)