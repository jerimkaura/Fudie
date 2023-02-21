package com.fudie.domain.use_cases

import com.fudie.domain.model.Meal
import com.fudie.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(): Flow<List<Meal>> {
        return repository.getFavouriteMeals()
    }
}