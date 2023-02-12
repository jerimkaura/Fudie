package com.cookpad.domain.use_cases

import com.cookpad.domain.model.Meal
import com.cookpad.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(): Flow<List<Meal>> {
        return repository.getFavouriteMeals()
    }
}