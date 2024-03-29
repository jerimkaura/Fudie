package com.fudie.core.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_surface_dark
import color_surface_light
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.MealCategory
import com.fudie.core.R

@Composable
fun CategoryItem(
    category: MealCategory,
    onClick: (mealCategory: MealCategory) -> Unit,
    openDialog: MutableState<Boolean>,
) {
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
                .clickable {
                    openDialog.value = true
                    onClick.invoke(category)
                }
                .background(itemBgColor, RoundedCornerShape(10.dp))
                .size(itemWidth - 10.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.strCategoryThumb)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(itemWidth - 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = category.strCategory, style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}