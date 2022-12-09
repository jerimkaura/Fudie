package com.cookpad.data.remote

import com.cookpad.data.remote.dto.CountriesResponse
import com.cookpad.data.remote.dto.IngredientsResponse
import com.cookpad.data.remote.dto.MealCategoriesResponse
import com.cookpad.data.remote.dto.MealsResponse
import retrofit2.http.GET

interface CookPadApiService {
    companion object {
        const val GET_ALL_INGREDIENTS = "list.php?i=list"
        const val GET_MEAL_CATEGORIES = "categories.php"
        const val GET_MEAL_COUNTRIES = "list.php?a=list"
        const val GET_MEAL_CHICKEN_MEALS = "filter.php?i=chicken_breast"
        const val GET_MEAL_BEEF_MEALS = "filter.php?i=Beef"
        const val GET_MEAL_PORK_MEALS = "filter.php?i=Pork"
        const val GET_MEAL_VEGAN_MEALS = "filter.php?c=vegetarian"
        const val GET_MEAL_RANDOM_MEAL = "random.php"
        const val GET_MEAL_BREAKFAST_MEAL = "filter.php?i=milk"
    }

    @GET(GET_ALL_INGREDIENTS)
    suspend fun getIngredients(): IngredientsResponse

    @GET(GET_MEAL_CATEGORIES)
    suspend fun getMealCategories(): MealCategoriesResponse

    @GET(GET_MEAL_COUNTRIES)
    suspend fun getMealCountries(): CountriesResponse

    @GET(GET_MEAL_CHICKEN_MEALS)
    suspend fun getChickenMeals(): MealsResponse

    @GET(GET_MEAL_BEEF_MEALS)
    suspend fun getBeefMeals(): MealsResponse

    @GET(GET_MEAL_PORK_MEALS)
    suspend fun getPorkMeals(): MealsResponse

    @GET(GET_MEAL_VEGAN_MEALS)
    suspend fun getVegetarianMeal(): MealsResponse

    @GET(GET_MEAL_RANDOM_MEAL)
    suspend fun getRandomMeal()

    @GET(GET_MEAL_BREAKFAST_MEAL)
    suspend fun getBreakfastMeal(): MealsResponse

}