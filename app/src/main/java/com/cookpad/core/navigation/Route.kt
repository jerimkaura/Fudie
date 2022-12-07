package com.cookpad.core.navigation

sealed class Route(val route:String){
    object HomeScreen : Route("home_screen")
    object SettingsScreen : Route("settings_screen")
    object ProfileScreen : Route("profile_screen")
    object RegisterScreen : Route("register_screen")
    object LoginScreen : Route("login_screen")
}
