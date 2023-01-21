package com.cookpad.core.screens.meals

import MealItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.R
import com.cookpad.core.screens.meals.components.SelectedMeal
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.core.ui.theme.montserrat

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
    val mealsState = mealViewModel.meals.value
    val categoryName = mealViewModel.categoryName.value

    Scaffold(
        content = { paddingValues ->
            if (mealsState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(
                        size = 70.dp,
                        lottieFile = R.raw.small_section_loader,
                        speed = 2.0f
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Hang on chef...")
                }

            } else if (mealsState.error.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(
                        size = 180.dp,
                        lottieFile = R.raw.no_internet,
                        speed = 2.0f
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = mealsState.error)
                }
            } else {
                val meals = mealsState.data ?: emptyList()
                if (meals.isNotEmpty()) {
                    Column( modifier = Modifier.padding(bottom = 10.dp)) {
                        SelectedMeal(meals,categoryName, recipeViewModel ,navController)
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2)
                        ) {
                            items(meals.size) {
                                MealItem(meals[it], recipeViewModel, navController)
                            }
                        }
                    }

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = paddingValues.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding()
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnime(
                            size = 100.dp,
                            lottieFile = R.raw.empty_list,
                            speed = 0.5f
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "No items, try connecting to the internet.",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                            )
                        )
                    }
                }
            }
        }
    )
}

