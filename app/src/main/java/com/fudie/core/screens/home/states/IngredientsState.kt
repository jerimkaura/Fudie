package com.fudie.core.screens.home.states

import com.fudie.domain.model.Ingredient

data class IngredientsState(
    var isLoading: Boolean = false,
    var data: List<Ingredient>? = null,
    var error: String = ""
)
