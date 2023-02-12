package com.cookpad.domain.use_cases

import com.cookpad.domain.repository.MealRepository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(isFavourite:Boolean, strMealId: String){
        repository.toggleFavourite(isFavourite, strMealId)
    }
}