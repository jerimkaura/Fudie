package com.fudie.domain.repository

import com.fudie.common.util.Resource
import com.fudie.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountries(): Flow<Resource<List<Country>>>
}