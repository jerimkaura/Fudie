package com.cookpad.core.navigation.bottomNavigation

import com.cookpad.core.R
import com.cookpad.core.navigation.Route

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String,
){
    object Home : BottomNavItem(
        "Home",
        R.drawable.ic_home,
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
}
