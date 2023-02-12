package com.cookpad.core


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.datastore.dataStore
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cookpad.core.app_settings.LocalRecipeSerializer
import com.cookpad.core.navigation.Route
import com.cookpad.core.navigation.bottomNavigation.BottomNavigationBar
import com.cookpad.core.screens.addplan.AddPlanScreen
import com.cookpad.core.screens.category.CategoriesScreen
import com.cookpad.core.screens.country.CountriesScreen
import com.cookpad.core.screens.favourites.FavouritesScreen
import com.cookpad.core.screens.home.HomeScreen
import com.cookpad.core.screens.ingredient.IngredientsScreen
import com.cookpad.core.screens.ingredient.SingleIngredientScreen
import com.cookpad.core.screens.meals.MealsScreen
import com.cookpad.core.screens.planner.PlannerScreen
import com.cookpad.core.screens.recipe.RecipeScreen
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.core.ui.theme.CookPadThem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by dataStore("local-recipe.json", LocalRecipeSerializer)

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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberAnimatedNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(false) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            Route.HomeScreen.route -> true
            Route.CategoriesScreen.route -> true
            Route.CountriesScreen.route -> true
            Route.PlannerScreen.route -> true
            Route.IngredientsScreen.route -> true
            Route.FavouritesScreen.route -> true
            else -> false
        }

        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(navController = navController)
                }
            },
        ) {
            AnimatedNavHost(navController = navController, startDestination = "home_screen") {
                screens(navController)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.screens(navController: NavController) {
    composable(
        route = Route.HomeScreen.route,
        enterTransition = {
            slideInHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                initialOffsetX = {
                    -it
                }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        },
    ) {
        HomeScreen(navController)
    }

    composable(route = Route.RecipeScreen.route + "/{meal_id}",
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(500),
                initialOffsetX = {
                    it
                }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(500),
                targetOffsetX = {
                    it
                }
            )
        }) {
        RecipeScreen(navController)
    }

    composable(route = Route.CategoriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                  -  it
                }
            )
        }) {
        CategoriesScreen(navController)
    }

    composable(route = Route.CountriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(1000))
        }) {
        CountriesScreen(navController)
    }
    

    composable(route = Route.MealsScreen.route + "/{category_name}",
        enterTransition = {
            fadeIn(animationSpec = tween(3000))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(3000))
        }) {
        MealsScreen(navController)
    }

    composable(route = Route.IngredientsScreen.route) {
        IngredientsScreen(navController)
    }
    composable(route = Route.CategoriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -  it
                }
            )
        }) {
        CategoriesScreen(navController)
    }

    composable(route = Route.SingleIngredientsScreen.route +"/{ingredient_name}",
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        }) {
        SingleIngredientScreen(navController)
    }

    composable(route = Route.PlannerScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        PlannerScreen(navController)
    }

    composable(route = Route.AddPlanScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        AddPlanScreen(navController)
    }

    composable(route = Route.FavouritesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        FavouritesScreen(navController)
    }
}