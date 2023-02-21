package com.fudie.core.screens.home.states

import com.fudie.domain.model.Country

data class CountriesState(
    var isLoading: Boolean = false,
    var data: List<Country>? = null,
    var error: String = ""
)