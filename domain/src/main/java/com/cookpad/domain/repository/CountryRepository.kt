package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountries(): Flow<Resource<List<Country>>>
}