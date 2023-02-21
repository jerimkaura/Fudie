package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.Country
import com.fudie.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountryRepository) {
    operator fun invoke(): Flow<Resource<List<Country>>> {

        return repository.getCountries()
    }
}