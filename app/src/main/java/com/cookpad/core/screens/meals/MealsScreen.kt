package com.cookpad.core.screens.meals

import MealItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.screens.meals.components.SelectedMeal
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.utils.getActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val activity = context.getActivity()
        val window = activity?.window
        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        val windowInsetsController =
            window?.let { ViewCompat.getWindowInsetsController(it.decorView) }

        windowInsetsController?.isAppearanceLightNavigationBars = true

    }
    val meals = mealViewModel.meals.value

    Scaffold(
        topBar = {
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                    .fillMaxSize(),
            ) {

                if (!meals.isLoading && meals.error.isEmpty()) {
                    if (meals.data!!.isNotEmpty()){
                        SelectedMeal(meals.data!!, navController)
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(.2f),
                            columns = GridCells.Fixed(3)
                        ) {
                            items(meals.data!!.size) {
                                MealItem(meals.data!![it],recipeViewModel, navController)
                            }
                        }
                    }

                }
            }
        }
    )
}

