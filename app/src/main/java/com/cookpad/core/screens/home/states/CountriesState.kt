package com.cookpad.core.screens.home.states

import com.cookpad.domain.model.Country

data class CountriesState(
    var isLoading: Boolean = false,
    var data: List<Country>? = null,
    var error: String = ""
)