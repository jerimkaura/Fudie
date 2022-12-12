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
        R.drawable.ic_trending,
        Route.CategoriesScreen.route
    )

    object Countries : BottomNavItem(
        "Countries",
        R.drawable.ic_notes,
        Route.CountriesScreen.route
    )
}
