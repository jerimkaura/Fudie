package com.fudie.core.screens.home

import MealItem
import MealItemShimmer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fudie.core.R
import com.fudie.core.screens.home.components.*
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.recipe.RecipeViewModels
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.ui.theme.WindowSizeClass
import com.fudie.core.ui.theme.montserrat
import com.fudie.core.ui.theme.rememberWindowSizeClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel(),
) {
    val mealCategories = homeViewModel.mealCategories.value
    val allMealsState = homeViewModel.allMeals.collectAsState().value
    homeViewModel.randomRecipe.value
    rememberCoroutineScope()
    val windowSize = rememberWindowSizeClass()

    val searchTextState by homeViewModel.searchTextState

    LaunchedEffect(Unit) {
        homeViewModel.getRandomRecipe()
    }
    Scaffold(
        topBar = {
            TopBarHomeScreen(
                text = searchTextState,
                onTextChange = {
                    homeViewModel.updateSearchTextState(it)
                    homeViewModel.searchMeal(it)
                },
                onClearClicked = {
                    homeViewModel.updateSearchTextState("")
                    homeViewModel.getAllMeals()
                },
                onSearchClicked = {
                    homeViewModel.searchMeal(it)
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding() + 60.dp,
                        top = paddingValues.calculateTopPadding()
                    )
                    .fillMaxSize()
            ) {
                MealCategorySection(mealCategories, navController)
                AllMealsSection(
                    allMealsState = allMealsState,
                    recipeViewModel,
                    navController,
                    windowSize,
                    homeViewModel
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
    windowSize: WindowSizeClass,
    homeViewModel: HomeViewModel
) {
    if (allMealsState.isLoading) {
        val itemWidth = ((LocalConfiguration.current.screenWidthDp - 30).toDouble() / 2).dp
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 20.dp), columns = GridCells.Fixed(2)
        ) {
            items(6) {
                MealItemShimmer()
            }
        }
    } else if (allMealsState.error.isNotEmpty()) {
        Text(text = allMealsState.error.toString())
    } else {
        val allMeals = allMealsState.data ?: emptyList()
        val itemWidth = ((LocalConfiguration.current.screenWidthDp - 30).toDouble() / 2).dp
        if (allMeals.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = 20.dp,
                    )
                    .fillMaxSize(),
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
                    text = "No Meals Found.",
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 20.dp), columns = GridCells.Fixed(2)
            ) {
                items(allMeals.size) { mealItem ->
                    MealItem(allMeals[mealItem], navController)
                }
            }
        }
    }

}