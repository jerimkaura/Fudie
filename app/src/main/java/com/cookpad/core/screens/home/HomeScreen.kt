package com.cookpad.core.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.screens.home.components.*
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat

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
    Scaffold(
        topBar = { TopBarHomeScreen(navController, onProfileClick = {}) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding()+60.dp,
                        top = paddingValues.calculateTopPadding() + 10.dp
                    )
                    .fillMaxSize()
            ) {
                item {
                    RandomMeal()
                }
                item {
                    SectionHeader("Browse by Category", onClick = {})
                    if (mealCategories.isLoading) {
                        LoadingAnimation()
                    } else if (mealCategories.error != "") {
                        ErrorComposable(mealCategories.error)
                    } else {
                        MealCategorySection(mealCategories.data!!)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Chicken Meals", onClick = {})
                    if (chickenMeals.isLoading) {
                        LoadingAnimation()
                    } else if (chickenMeals.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        MealsByCategory(chickenMeals.data!!)
                    }
                }
                item {
                    RowSpacer()
                }
                item {

                    SectionHeader("Explore Countries' Meals", onClick = {})
                    if (countries.isLoading) {
                        LoadingAnimation()
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        MealCountrySection(countries.data!!)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Love animals, Eat veggies", onClick = {})
                    if (ingredients.isLoading) {
                        LoadingAnimation()
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        SpecialMealCategory(vegetarianMeals.data!!)
                    }
                }
                item {
                    RowSpacer()
                }

                item {
                    SectionHeader("Have an Awesome Breakfast", onClick = {})
                    if (ingredients.isLoading) {
                        LoadingAnimation()
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        RoundMealCardSection(breakfastMeals.data!!)
                    }
                }

                item {
                    RowSpacer()
                }

                item {
                    SectionHeader("Browse by Ingredients", onClick = {})
                    if (ingredients.isLoading) {
                        LoadingAnimation()
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {

                        MealIngredients(ingredients.data!!)
                    }
                }
                item {
                    RowSpacer()
                }
                item {
                    SectionHeader("Pork Meals", onClick = {})
                    if (ingredients.isLoading) {
                        LoadingAnimation()
                    } else if (countries.error != "") {
                        ErrorComposable(countries.error)
                    } else {
                        SpecialMealCategory(porkMeals.data!!)
                    }
                }
            }
        }
    )
}

@Composable
fun ErrorComposable(error: String) {

}

@Composable
fun LoadingAnimation() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Please wait...",
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        )
    }
}

