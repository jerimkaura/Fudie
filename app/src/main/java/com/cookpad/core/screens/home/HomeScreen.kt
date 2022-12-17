package com.cookpad.core.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import color_primary_light
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.components.*
import com.cookpad.core.screens.recipe.RecipeViewModels
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel(),
) {
    val ingredients = viewModel.ingredients.value
    val mealCategories = viewModel.mealCategories.value
    val countries = viewModel.countries.value
    val chickenMeals = viewModel.chickenMeals.value
    val porkMeals = viewModel.porkMeals.value
    val vegetarianMeals = viewModel.vegetarianMeals.value
    val breakfastMeals = viewModel.breakfastMeals.value
    val randomRecipe = viewModel.randomRecipe.value
    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getRandomRecipe()
    }
    Scaffold(
        topBar = { TopBarHomeScreen(navController, onProfileClick = {}) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + 60.dp,
                    top = paddingValues.calculateTopPadding() + 10.dp
                )
                .fillMaxSize()
        ) {
            item {
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
            }
            item {
                MealCategorySection(mealCategories, navController)
            }
            item {
                RowSpacer()
            }
            item {
                MealsByCategory(chickenMeals, recipeViewModel, navController)
            }
            item {
                RowSpacer()
            }
            item {
                MealCountrySection(countries, navController, viewModel)
            }
            item {
                RowSpacer()
            }
            item {
                SpecialMealCategory(vegetarianMeals, navController, title="Vegetarian")
            }
            item {
                RowSpacer()
            }

            item {
                RoundMealCardSection(breakfastMeals, navController)
            }

            item {
                RowSpacer()
            }

            item {
                MealIngredients(ingredients, navController)
            }
            item {
                RowSpacer()
            }
            item {
                SpecialMealCategory(porkMeals, navController, title="Pork")
            }
        }
    }
}

@Composable
fun LoadingAnimation(height: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(height),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier,
            strokeWidth = 3.dp,
            color = color_primary_light
        )
    }
}

