package com.cookpad.core.screens.country.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cookpad.core.R
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.utils.LottieAnime

@Composable
fun CountryMealsSection(
    mealsState: MealsState,
    recipeViewModel: RecipeViewModels,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val scope = rememberCoroutineScope()
    if (mealsState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnime(size = 70.dp, lottieFile = R.raw.loader, speed = 2.5f)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Hang on chef...")
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


