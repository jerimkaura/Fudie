package com.fudie.core.screens.home.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import color_surface_dark
import color_surface_light
import com.fudie.core.R
import com.fudie.core.navigation.Route
import com.fudie.core.screens.home.states.MealCategoriesState
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.screens.utils.SectionHeader
import com.fudie.core.ui.theme.montserrat

@Composable
fun MealCategorySection(
    mealCategoriesState: MealCategoriesState,
    navController: NavController
) {
    val itemBgColor = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    SectionHeader("Explore Categories", onClick = {
        navController.navigate(
            Route
                .CategoriesScreen
                .route
        )
    })

    LazyRow {
        if (mealCategoriesState.data !== null) {
            val mealCategories = mealCategoriesState.data ?: emptyList()
            if (mealCategories.isNotEmpty()) {
                items(mealCategories.size) { index ->
                    MealCategoryItem(mealCategories[index], navController)
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(120.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnime(size = 70.dp, lottieFile = R.raw.empty_list, speed = 2.0f)
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = "No Items Found, check your internet",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        } else if (mealCategoriesState.isLoading) {
            items(10) {
                LoadingCategoryShimmer()
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
                    Text(text = mealCategoriesState.error)
                }
            }
        }
    }
}