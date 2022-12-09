package com.cookpad.domain.repository

import com.cookpad.common.util.Resource
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository  {
    fun getChickenMeals(): Flow<Resource<List<Meal>>>
    fun getBeefMeals(): Flow<Resource<List<Meal>>>
    fun getPorkMeals(): Flow<Resource<List<Meal>>>
    fun getVegetarianMeals(): Flow<Resource<List<Meal>>>
    fun getBreakFastMeals(): Flow<Resource<List<Meal>>>
}