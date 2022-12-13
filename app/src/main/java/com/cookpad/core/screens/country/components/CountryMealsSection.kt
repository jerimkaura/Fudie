package com.cookpad.core.screens.country.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.ui.theme.montserrat
import kotlinx.coroutines.launch

@Composable
fun CountryMealsSection(
    mealsState: MealsState,
    recipeViewModel: RecipeViewModels,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    if (mealsState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnime(size = 180.dp, lottieFile = R.raw.veggies, speed = 0.50f)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Hang on chef...")

        }
    }
    if (!mealsState.isLoading && mealsState.error.isEmpty()) {
        val meals = mealsState.data ?: listOf()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(meals.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 1.dp, vertical = 0.dp)
                        .width(140.dp)
                        .height(120.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(modifier = Modifier
                        .clickable {
                            scope.launch {
                                recipeViewModel.getRecipeByMealId(mealId = meals[it].idMeal)
                                navController.navigate(
                                    Route.RecipeScreen.route + "/${meals[it].idMeal}"
                                )
                            }

                        }
                        .wrapContentSize(),
                        shape = RoundedCornerShape(5.dp),
                        elevation = CardDefaults.cardElevation(1.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(meals[it].strMealThumb).crossfade(true)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .build(),
                            contentDescription = stringResource(R.string.app_name),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(130.dp)
                                .height(90.dp),
                        )
                    }

                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = meals[it].strMeal,
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            lineHeight = 0.8.em,
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}


