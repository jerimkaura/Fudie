package com.cookpad.data.remote.dto


import com.squareup.moshi.Json

data class CountriesResponse(
    @Json(name = "mealDTOS")
    val meals: List<CountryDTO>?
)