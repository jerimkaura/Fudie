package com.cookpad.core.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import color_surface_dark
import color_surface_light
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.MealCategory

@Composable
fun MealCategorySection(mealCategories: List<MealCategory>, navController: NavController) {
    val itemBgColor = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    LazyRow(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 10.dp),
    ) {
        items(mealCategories.size) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 20.dp, horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(itemBgColor, RoundedCornerShape(10.dp))
                        .size(70.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(mealCategories[it].strCategoryThumb),
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .size(70.dp)
                            .clickable { },
                        contentScale = ContentScale.Inside,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = mealCategories[it].strCategory, style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                )
            }
        }
    }
}