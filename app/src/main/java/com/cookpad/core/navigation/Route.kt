package com.cookpad.core.navigation

sealed class Route(val route:String){
    object HomeScreen : Route("home_screen")
    object RecipeScreen : Route("recipe_screen")
    object MealBoxScreen : Route("meal_box_screen")
    object ProfileScreen : Route("profile_screen")
    object CategoriesScreen : Route("categories_screen")
    object MealsScreen : Route("meals_screen")
    object CountriesScreen : Route("countries_screen")
}
