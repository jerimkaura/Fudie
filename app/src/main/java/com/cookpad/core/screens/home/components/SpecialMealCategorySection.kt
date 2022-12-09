package com.cookpad.core.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialMealCategory(meals: List<Meal>) {
    val itemBgColor = if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_primary_light
    val badgeColor = if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_surface_light
    LazyRow(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        items(meals.size) {
            Card(
                modifier = Modifier
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
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = rememberAsyncImagePainter(meals[it].strMealThumb),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AssistChip(
                                border = AssistChipDefaults.assistChipBorder(
                                    borderColor = badgeColor.copy(0.2f)
                                ),
                                shape = RoundedCornerShape(20.dp),
                                onClick = { /* Do something! */ },
                                label = { Text(text = "4.5") },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_rate),
                                        contentDescription = "Localized description",
                                        Modifier.size(AssistChipDefaults.IconSize)
                                    )
                                },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = badgeColor,
                                )
                            )


                        }

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

                    Spacer(modifier = Modifier.height(1.dp))
                }
            }
        }
    }
}