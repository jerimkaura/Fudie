package com.cookpad.core.screens.ingredient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.screens.ingredient.components.TopBarIngredientsScreen
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleIngredientScreen(
    navController: NavController,
    ingredientsViewModel: IngredientsViewModel = hiltViewModel(),
) {
    val mealsState = ingredientsViewModel.meals.collectAsState().value
    Scaffold(
        topBar = {
            TopBarIngredientsScreen()
        },
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
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = paddingValues.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding()
                            )
                            .fillMaxSize(),
                        columns = GridCells.Fixed(3)
                    ) {
                        items(meals.size) {
                            IngredientMeal(meals[it], navController)
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

@Composable
fun IngredientMeal(meal: Meal, navController: NavController) {
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

            }
            .wrapContentSize(),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(1.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.strMealThumb).crossfade(true)
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
            text = meal.strMeal,
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