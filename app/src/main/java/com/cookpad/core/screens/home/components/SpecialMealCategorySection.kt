package com.cookpad.core.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialMealCategory(mealsState: MealsState, navController: NavController, title: String) {
    val itemBgColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_primary_light
    val badgeColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_surface_light
    SectionHeader(title, onClick = {
        navController.navigate(
            Route
                .MealsScreen
                .route + "/${"Vegetarian"}"
        )
    })


    LazyRow(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        if (mealsState.isLoading) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.small_section_loader, speed = 1.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material.Text(
                        text = "Hang on chef...",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        } else if (mealsState.error.isNotEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.no_internet, speed = 2.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material.Text(
                        text = "Check you connection", style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        } else {
            val meals = mealsState.data ?: emptyList()
            if (meals.isNotEmpty()) {
                items(meals.size) {
                    Card(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    Route
                                        .RecipeScreen
                                        .route + "/${meals[it].idMeal}"
                                )
                            }
                            .wrapContentSize()
                            .padding(vertical = 0.dp, horizontal = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(0.dp))
                            ) {
                                SubcomposeAsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(meals[it].strMealThumb)
                                        .crossfade(true)
                                        .diskCachePolicy(CachePolicy.ENABLED)
                                        .build(),
                                    contentDescription = stringResource(R.string.app_name),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    loading = {
                                        LottieAnime(
                                            size = 10.dp,
                                            lottieFile = R.raw.veggies,
                                            speed = 1.0f
                                        )
                                    }
                                )

                                Row(
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .background(itemBgColor.copy(0.9f))
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = Modifier.padding(5.dp),
                                        text = meals[it].strMeal,
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
                        }
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(150.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnime(size = 80.dp, lottieFile = R.raw.empty_list, speed = 2.0f)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "No Items Found", style = TextStyle(
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
}