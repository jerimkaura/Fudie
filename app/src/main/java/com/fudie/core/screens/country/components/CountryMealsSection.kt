package com.fudie.core.screens.country.components

import MealItemShimmer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fudie.core.screens.home.HomeViewModel
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.recipe.RecipeViewModels

@Composable
fun CountryMealsSection(
    mealsState: MealsState,
    recipeViewModel: RecipeViewModels,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val scope = rememberCoroutineScope()
    if (mealsState.isLoading) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, top = 5.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(6) {
                MealItemShimmer()
            }
        }
    }
    if (!mealsState.isLoading && mealsState.error.isEmpty()) {
        val meals = mealsState.data ?: listOf()
        val itemWidth = ((LocalConfiguration.current.screenWidthDp - 30).toDouble() / 2).dp
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, top = 5.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(meals.size) { meal->
                CountryMealItem(
                    meal = meals[meal],
                    navController = navController,
                )
            }
        }
    }
}


