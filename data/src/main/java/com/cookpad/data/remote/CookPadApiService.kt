package com.cookpad.data.remote

import com.cookpad.data.remote.dto.CountriesResponse
import com.cookpad.data.remote.dto.IngredientsResponse
import com.cookpad.data.remote.dto.MealCategoriesResponse
import retrofit2.http.GET

interface CookPadApiService {
    companion object {
        const val GET_ALL_INGREDIENTS = "list.php?i=list"
        const val GET_MEAL_CATEGORIES = "categories.php"
        const val GET_MEAL_COUNTRIES = "list.php?a=list"
    }

    @GET(GET_ALL_INGREDIENTS)
    suspend fun getIngredients(): IngredientsResponse

    @GET(GET_MEAL_CATEGORIES)
    suspend fun getMealCategories(): MealCategoriesResponse

    @GET(GET_MEAL_COUNTRIES)
    suspend fun getMealCountries(): CountriesResponse
}