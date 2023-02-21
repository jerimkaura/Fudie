package com.fudie.core.navigation

sealed class Route(val route:String){
    object HomeScreen : Route("home_screen")
    object RecipeScreen : Route("recipe_screen")
    object CategoriesScreen : Route("categories_screen")
    object MealsScreen : Route("meals_screen")
    object CountriesScreen : Route("countries_screen")
    object IngredientsScreen : Route("ingredients_screen")
    object SingleIngredientsScreen : Route("single_ingredients_screen")
    object PlannerScreen : Route("planner_screen")
    object AddPlanScreen : Route("add_plan_screen")
    object FavouritesScreen : Route("favourites_screen")
}
