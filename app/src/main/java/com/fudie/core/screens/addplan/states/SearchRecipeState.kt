package com.fudie.core.screens.addplan.states

import com.fudie.domain.model.Recipe

data class SearchRecipeState(
    var isLoading: Boolean = false,
    var data: List<Recipe>? = null,
    var error: String = ""
)
