package com.fudie.core.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_surface_dark
import color_surface_light
import com.fudie.core.screens.utils.shimmerEffect
import com.fudie.core.R
@Composable
fun CategoryItemShimmer() {
    val itemWidth = ((LocalConfiguration.current.screenWidthDp - 20).toDouble() / 3).dp
    val itemBgColor = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 0.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(itemBgColor, RoundedCornerShape(10.dp))
                .size(itemWidth - 10.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Inside,
                modifier = Modifier.shimmerEffect()
                    .clip(RoundedCornerShape(5.dp))
                    .size(itemWidth - 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.clip(RoundedCornerShape(2.dp))
                .width(itemWidth - 10.dp)
                .shimmerEffect()
                .height(10.dp)

        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}