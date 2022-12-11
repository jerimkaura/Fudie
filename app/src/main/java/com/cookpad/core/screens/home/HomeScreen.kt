package com.cookpad.core.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import color_primary_light
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.components.*
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val ingredients = viewModel.ingredients.value
    val mealCategories = viewModel.mealCategories.value
    val countries = viewModel.countries.value
    val chickenMeals = viewModel.chickenMeals.value
    val beefMeals = viewModel.beefMeals.value
    val porkMeals = viewModel.porkMeals.value
    val vegetarianMeals = viewModel.vegetarianMeals.value
    val breakfastMeals = viewModel.breakfastMeals.value
    val randomRecipe = viewModel.randomRecipe.value
    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getRandomRecipe()
    }
    Scaffold(
        topBar = { TopBarHomeScreen(navController, onProfileClick = {}) },
        content = { paddingValues ->
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
                            navController.navigate(
                                Route
                                    .RecipeScreen
                                    .route + "/${randomRecipe.data?.idMeal}"
                            )
                        }
                    })
                }
                item {
                    SectionHeader(
                        "Browse by Category",
                        onClick = { navController.navigate(Route.CategoriesScreen.route) })
                    if (mealCategories.isLoading) {
                        LoadingAnimation(180.dp)
                    } else if (mealCategories.error != "") {
                        ErrorComposable(mealCategories.error)
                    } else {
                        MealCategorySection(mealCategories.data!!, navController)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Chicken Meals", onClick = {
                        navController.navigate(
                            Route
                                .MealsScreen
                                .route + "/${"Chicken"}"
                        )
                    })
                    if (chickenMeals.isLoading) {
                        LoadingAnimation(180.dp)
                    } else if (chickenMeals.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        MealsByCategory(chickenMeals.data!!, navController)
                    }
                }
                item {
                    RowSpacer()
                }
                item {

                    SectionHeader("Explore Countries' Meals", onClick = {})
                    if (countries.isLoading) {
                        LoadingAnimation(120.dp)
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        MealCountrySection(countries.data!!, navController)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Love animals, Eat veggies", onClick = {
                        navController.navigate(
                            Route
                                .MealsScreen
                                .route + "/${"Vegetarian"}"
                        )
                    })
                    if (ingredients.isLoading) {
                        LoadingAnimation(180.dp)
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        SpecialMealCategory(vegetarianMeals.data!!, navController)
                    }
                }
                item {
                    RowSpacer()
                }

                item {
                    SectionHeader("Have an Awesome Breakfast", onClick = {
                        navController.navigate(
                            Route
                                .MealsScreen
                                .route + "/${"Milk"}"
                        )
                    })
                    if (ingredients.isLoading) {
                        LoadingAnimation(140.dp)
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        RoundMealCardSection(breakfastMeals.data!!, navController)
                    }
                }

                item {
                    RowSpacer()
                }

                item {
                    SectionHeader("Browse by Ingredients", onClick = {})
                    if (ingredients.isLoading) {
                        LoadingAnimation(180.dp)
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {

                        MealIngredients(ingredients.data!!, navController)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Pork Meals", onClick = {
                        navController.navigate(
                            Route
                                .MealsScreen
                                .route + "/${"Pork"}"
                        )
                    })
                    if (ingredients.isLoading) {
                        LoadingAnimation(150.dp)
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        SpecialMealCategory(porkMeals.data!!, navController)
                    }
                }
            }
        }
    )
}

@Composable
fun ErrorComposable(error: String, modifier: Modifier = Modifier) {

}

@Composable
fun LoadingAnimation(height: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(height),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier,
            strokeWidth = 5.dp,
            color = color_primary_light
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hang on...",
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        )
    }
}

