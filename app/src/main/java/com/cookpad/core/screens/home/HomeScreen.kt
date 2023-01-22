package com.cookpad.core.screens.home

import MealItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.components.*
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.ui.theme.WindowSizeClass
import com.cookpad.core.ui.theme.rememberWindowSizeClass
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel(),
) {
    val mealCategories = viewModel.mealCategories.value
    val allMealsState = viewModel.allMeals.value
    val randomRecipe = viewModel.randomRecipe.value
    val scope = rememberCoroutineScope()
    val windowSize = rememberWindowSizeClass()

    LaunchedEffect(Unit) {
        viewModel.getRandomRecipe()
    }
    Scaffold(
        content = { paddingValues->
            Column(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = paddingValues.calculateTopPadding()
                    )
                    .fillMaxSize()
            ) {
                RandomMeal(randomRecipe, onClick = {
                    scope.launch {
                        randomRecipe.data?.idMeal?.let {
                            recipeViewModel.getRecipeByMealId(it)
                            navController.navigate(
                                Route
                                    .RecipeScreen
                                    .route + "/${randomRecipe.data?.idMeal}"
                            )
                        }

                    }
                })
                MealCategorySection(mealCategories, navController)
                AllMealsSection(
                    allMealsState = allMealsState,
                    recipeViewModel,
                    navController,
                    windowSize
                )
            }
        }
    )
}

@Composable
fun AllMealsSection(
    allMealsState: MealsState,
    recipeViewModel: RecipeViewModels,
    navController: NavController,
    windowSize: WindowSizeClass
) {
    val allMeals = allMealsState.data ?: emptyList()
    val itemWidth = ((LocalConfiguration.current.screenWidthDp - 20).toDouble() / 2).dp
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(), columns = GridCells.Fixed(2)
    ) {
        items(allMeals.size) { mealItem ->
            MealItem(allMeals[mealItem], recipeViewModel, navController, itemWidth)
        }
    }
}