package com.cookpad.core.navigation

sealed class Route(val route:String){
    object HomeScreen : Route("home_screen")
    object SearchScreen : Route("search_screen")
    object MealBoxScreen : Route("meal_box_screen")
    object ProfileScreen : Route("profile_screen")
}
