package com.cookpad.core.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.states.CountriesState
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat

@Composable
fun MealCountrySection(countriesState: CountriesState, navController: NavController) {
    SectionHeader("Explore Countries' Meals", onClick = {
        navController.navigate(Route.CountriesScreen.route)
    })
    LazyRow {
        if (countriesState.isLoading) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(90.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.loader, speed = 1.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material.Text(text = "Loading countries...")
                }
            }
        } else if (countriesState.error.isNotEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(90.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 50.dp, lottieFile = R.raw.no_internet, speed = 2.0f)
                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material.Text(text = countriesState.error)
                }
            }
        } else {
            val countries = countriesState.data ?: emptyList()
            if (countries.isNotEmpty()) {
                items(countries.size) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 25.dp, horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(countries[it].flagUrl)
                                .crossfade(true)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .build(),
                            contentDescription = stringResource(R.string.app_name),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .width(80.dp)
                                .height(50.dp)
                                .clickable { },
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = countries[it].strArea, style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                            )
                        )
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(90.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnime(size = 50.dp, lottieFile = R.raw.veggies, speed = 2.0f)
                        Spacer(modifier = Modifier.height(10.dp))
                        androidx.compose.material.Text(text = "No Items, check your connection")
                    }
                }
            }
        }
    }
}

