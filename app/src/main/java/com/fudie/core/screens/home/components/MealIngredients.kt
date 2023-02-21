package com.fudie.core.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fudie.core.R
import com.fudie.core.navigation.Route
import com.fudie.core.screens.home.states.IngredientsState
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.screens.utils.SectionHeader
import com.fudie.core.ui.theme.montserrat

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealIngredients(ingredientsState: IngredientsState, navController: NavController) {
    val boxColor = if (isSystemInDarkTheme()) Color(0xFF1e2025) else Color(0xFFF8F6F8)
    val chipBorder = if (isSystemInDarkTheme()) Color(0XFF2F2E41) else Color(0xFFfed69a)

    SectionHeader("Popular Ingredients", onClick = {})
    if (ingredientsState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnime(size = 50.dp, lottieFile = R.raw.loader, speed = 2.0f)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Hang on chef...", style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                )
            )
        }
    } else if (ingredientsState.error.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnime(size = 80.dp, lottieFile = R.raw.loader, speed = 2.0f)
            Spacer(modifier = Modifier.height(10.dp))
            androidx.compose.material.Text(
                text = ingredientsState.error, style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                )
            )
        }
    } else {
        val ingredients = ingredientsState.data ?: emptyList()
        if (ingredients.isNotEmpty()) {
            LazyHorizontalStaggeredGrid(
                modifier = Modifier.height(180.dp),
                rows = StaggeredGridCells.Fixed(5),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(ingredients.size) {
                    AssistChip(
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = chipBorder
                        ),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            navController.navigate(Route.SingleIngredientsScreen.route + "/${ingredients[it].strIngredient}")
                        },
                        label = {
                            Text(
                                text = ingredients[it].strIngredient,
                                style = TextStyle(
                                    fontFamily = montserrat,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trending),
                                contentDescription = "Localized description",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = boxColor,
                        )
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnime(size = 80.dp, lottieFile = R.raw.empty_list, speed = 2.0f)
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material.Text(
                    text = "No items found, check your internet", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                )
            }
        }
    }
}