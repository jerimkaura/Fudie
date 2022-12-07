package com.cookpad.core.screens.home.states

import com.cookpad.domain.model.Ingredient

data class IngredientsState(
    var isLoading: Boolean = false,
    var data: List<Ingredient>? = null,
    var error: String = ""
)
