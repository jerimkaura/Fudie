package com.cookpad.core.screens.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_light
import com.cookpad.core.R
import com.cookpad.domain.model.Recipe

@Composable
fun MealImage(recipe: Recipe?, navController: NavController) {
    val boxBackground = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val iconTint = color_surface_light
    Box(
        Modifier
            .padding(vertical = 0.dp, horizontal = 0.dp)
            .height(300.dp)
            .background(Color(0xff000000).copy(0.2f))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe?.strMealThumb)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(vertical = 36.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .background(boxBackground, CircleShape)
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigateUp()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    tint = iconTint
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .background(boxBackground, CircleShape)
                    .size(30.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_fav),
                    contentDescription = "",
                    tint = iconTint
                )
            }
        }
    }
}