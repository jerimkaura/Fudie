package com.cookpad.core.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal

@Composable
fun RoundMealCardSection(meals: List<Meal>, navController: NavController) {
    val itemBgColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_primary_light
    val badgeColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_surface_light
    LazyRow(
        modifier = Modifier
            .padding(vertical = 10.dp),
    ) {
        items(meals.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 5.dp)
                    .width(120.dp)
                    .height(140.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(
                                Route
                                    .RecipeScreen
                                    .route + "/${meals[it].idMeal}"
                            )
                        }
                        .wrapContentSize(),
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(meals[it].strMealThumb)
                            .crossfade(true)
                            .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                            .build(),
                        placeholder = painterResource(R.drawable.lily),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(110.dp),
                    )
                }

                Text(
                    modifier = Modifier.padding(5.dp),
                    text = meals[it].strMeal,
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 0.8.em,
                    ),
                    overflow = TextOverflow.Ellipsis, maxLines = 1
                )
            }
        }
    }
}