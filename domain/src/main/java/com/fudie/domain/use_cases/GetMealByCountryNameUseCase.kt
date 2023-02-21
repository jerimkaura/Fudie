package com.fudie.domain.use_cases

import com.fudie.common.util.Resource
import com.fudie.domain.model.Meal
import com.fudie.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealByCountryNameUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(countryName: String): Flow<Resource<List<Meal>>>{
        return repository.getMealByCountryName(countryName)
    }
}