package com.fudie.data.remote

import com.fudie.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

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
        const val GET_MEAL_RECIPE_BY_ID = "lookup.php"
        const val GET_MEALS_BY_CATEGORY_NAME = "filter.php"
        const val GET_MEALS_BY_INGREDIENT_NAME = "filter.php"
        const val GET_MEALS_BY_COUNTRY_NAME = "filter.php"
        const val SEARCH_MEAL = "search.php"
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
    suspend fun getRandomMeal(): MealRecipeResponse

    @GET(GET_MEAL_BREAKFAST_MEAL)
    suspend fun getBreakfastMeal(): MealsResponse

    @GET(GET_MEAL_RECIPE_BY_ID)
    suspend fun getRecipeByMealId(@Query(value = "i") mealId: String): MealRecipeResponse

    @GET(GET_MEALS_BY_CATEGORY_NAME)
    suspend fun getMealsByCategoryName(@Query(value = "c") categoryName: String): MealsResponse

    @GET(GET_MEALS_BY_INGREDIENT_NAME)
    suspend fun getMealsByIngredientName(@Query(value = "i") categoryName: String): MealsResponse

    @GET(GET_MEALS_BY_COUNTRY_NAME)
    suspend fun getMealByCountryName(@Query(value = "a") countryName: String): MealsResponse

    @GET(SEARCH_MEAL)
    suspend fun searchMeal(@Query(value = "s") searchString: String): MealRecipeResponse
}