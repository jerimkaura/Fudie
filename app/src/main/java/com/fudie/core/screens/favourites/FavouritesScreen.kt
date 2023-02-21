package com.fudie.core.screens.favourites

import MealItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fudie.core.screens.favourites.components.TopBarFavouritesScreen
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.ui.theme.montserrat
import com.fudie.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {
    val favouriteMealsState by favouritesViewModel.favouritesMeals.collectAsState(initial = MealsState())
    Scaffold(
        topBar = {
            TopBarFavouritesScreen()
        },
        content = { paddingValues ->
            if (favouriteMealsState.isLoading) {
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
                        lottieFile = R.raw.loader,
                        speed = 2.0f
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Hang on chef...",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }

            } else if (favouriteMealsState.error.isNotEmpty()) {
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
                    Text(text = favouriteMealsState.error)
                }
            } else {
                val meals = favouriteMealsState.data ?: emptyList()
                if (meals.isNotEmpty()) {
                    Column(modifier = Modifier.padding(bottom = 10.dp)) {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                    bottom = paddingValues.calculateBottomPadding() + 80.dp
                                )
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2)
                        ) {
                            items(meals.size) { index ->
                                MealItem(meals[index], navController)
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
                            text = "No Saved Recipes.",
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
