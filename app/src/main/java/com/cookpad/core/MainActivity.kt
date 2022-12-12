package com.cookpad.core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.category.CategoriesScreen
import com.cookpad.core.screens.country.CountriesScreen
import com.cookpad.core.screens.home.BottomNavigationBar
import com.cookpad.core.screens.home.HomeScreen
import com.cookpad.core.screens.meals.MealsScreen
import com.cookpad.core.screens.recipe.RecipeScreen
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.core.ui.theme.CookPadThem
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookPadThem {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val context = LocalContext.current
    val activity = context.getActivity()
    val window = activity?.window
    if (window != null) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    val windowInsetsController =
        window?.let { ViewCompat.getWindowInsetsController(it.decorView) }

    windowInsetsController?.isAppearanceLightNavigationBars = true

//    val systemUiController = rememberSystemUiController()
//    val useDarkIcons = !isSystemInDarkTheme()
//    DisposableEffect(systemUiController, useDarkIcons) {
//        systemUiController.setSystemBarsColor(
//            color = Color(0xff000000).copy(alpha = 0.10F),
//            darkIcons = useDarkIcons
//        )
//
//        onDispose {}
//    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(false) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            Route.HomeScreen.route -> true
            Route.CategoriesScreen.route -> true
            else -> false
        }

        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(navController = navController)
                }
            },
        ) {
            NavHost(navController = navController, startDestination = "countries_screen") {
                screens(navController)
            }
        }
    }
}

private fun NavGraphBuilder.screens(navController: NavController){
    composable(route = Route.HomeScreen.route){
        HomeScreen(navController)
    }

    composable(route = Route.RecipeScreen.route + "/{meal_id}"){
        RecipeScreen(navController)
    }

    composable(route = Route.CategoriesScreen.route){
        CategoriesScreen(navController)
    }

    composable(route = Route.CountriesScreen.route){
        CountriesScreen(navController)
    }

    composable(route = Route.MealsScreen.route + "/{category_name}"){
        MealsScreen(navController)
    }
}