package com.fudie.domain.use_cases

import com.fudie.domain.repository.MealRepository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(isFavourite:Boolean, strMealId: String){
        repository.toggleFavourite(isFavourite, strMealId)
    }
}