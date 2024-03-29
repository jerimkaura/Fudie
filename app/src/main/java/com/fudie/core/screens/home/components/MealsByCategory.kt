package com.fudie.core.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import color_primary_light
import color_surface_dark
import color_surface_light
import com.fudie.core.navigation.Route
import com.fudie.core.screens.home.states.MealsState
import com.fudie.core.screens.recipe.RecipeViewModels
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.screens.utils.SectionHeader
import com.fudie.core.ui.theme.montserrat
import kotlinx.coroutines.launch
import com.fudie.core.R
@Composable
fun MealsByCategory(
    mealsState: MealsState,
    recipeViewModel: RecipeViewModels,
    navController: NavController
) {
    val itemBgColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.1f) else color_primary_light
    val scope = rememberCoroutineScope()
    LocalContext.current
    SectionHeader("Chicken Meals", onClick = {
        navController.navigate(
            Route
                .MealsScreen
                .route + "/${"Chicken"}"
        )
    })

    LazyRow(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        if (mealsState.data !== null) {
            val chickenMeals = mealsState.data ?: emptyList()
            if (chickenMeals.isNotEmpty()) {
                items(chickenMeals.size) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 0.dp, horizontal = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xff000000).copy(0.1f))
                                .width(250.dp)
                                .height(180.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(0.dp))
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(chickenMeals[it].strMealThumb)
                                        .crossfade(true)
                                        .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                                        .build(),
                                    contentDescription = stringResource(R.string.app_name),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            scope.launch {
                                                recipeViewModel.getRecipeByMealId(mealId = chickenMeals[it].idMeal)
                                                navController.navigate(
                                                    Route.RecipeScreen.route + "/${chickenMeals[it].idMeal}"
                                                )
                                            }
                                        },
                                )
                                Row(
                                    modifier = Modifier.background(itemBgColor.copy(0.8f))
                                        .align(Alignment.BottomCenter)
                                        .fillMaxWidth()
                                        .height(30.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = Modifier.padding(5.dp),
                                        text = chickenMeals[it].strMeal,
                                        style = TextStyle(
                                            color = color_surface_light,
                                            fontFamily = montserrat,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp,
                                            lineHeight = 0.8.em,
                                        ),
                                        overflow = TextOverflow.Ellipsis, maxLines = 1
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.height(1.dp))
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(180.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnime(size = 80.dp, lottieFile = R.raw.empty_list, speed = 2.0f)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "No Items Found, try checking your internet",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                            )
                        )
                    }
                }
            }
        } else if (mealsState.isLoading) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.loader, speed = 1.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Loading...",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        } else {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.no_internet, speed = 2.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = mealsState.error,
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
}