package com.fudie.core.navigation.bottomNavigation

import com.fudie.core.R
import com.fudie.core.navigation.Route

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String,
){
    object Home : BottomNavItem(
            "Discover",
        R.drawable.ic_discover,
        Route.HomeScreen.route
    )
    object Categories : BottomNavItem(
        "Categories",
        R.drawable.ic_categories,
        Route.CategoriesScreen.route
    )

    object Countries : BottomNavItem(
        "Cuisines",
        R.drawable.ic_flag,
        Route.CountriesScreen.route
    )

    object Ingredients : BottomNavItem(
        "Planner",
        R.drawable.ic_plan_add,
        Route.PlannerScreen.route
    )

    object Favourites : BottomNavItem(
        "Saved",
        R.drawable.ic_favourite_outline,
        Route.FavouritesScreen.route
    )
}
