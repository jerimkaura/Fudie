package com.fudie.core.screens.ingredient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import color_primary_light
import com.fudie.core.R
import com.fudie.core.navigation.Route
import com.fudie.core.screens.home.HomeViewModel
import com.fudie.core.screens.ingredient.components.TopBarIngredientsScreen
import com.fudie.core.screens.utils.LottieAnime
import com.fudie.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val ingredientsState = homeViewModel.ingredients.value
    Scaffold(
        topBar = {
            TopBarIngredientsScreen()
        },
        content = { paddingValues ->
            val ingredients = ingredientsState.data ?: emptyList()
            if (ingredients.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding() + 60.dp,
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(ingredients.size) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(Route.SingleIngredientsScreen.route + "/${ingredients[it].strIngredient}")
                                    }
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(horizontal = 3.dp),
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_trending),
                                    contentDescription = "",
                                    tint = color_primary_light
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(weight = 3f, fill = false)
                                            .padding(start = 10.dp)
                                    ) {

                                        Text(
                                            text = ingredients[it].strIngredient,
                                            style = TextStyle(
                                                fontFamily = montserrat,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp,
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = ingredients[it].strDescription,
                                            style = TextStyle(
                                                fontFamily = montserrat,
                                                fontSize = 12.sp,
                                                letterSpacing = (0.8).sp,
                                                color = Color.Gray,
                                            ),
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (ingredientsState.error.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 180.dp, lottieFile = R.raw.no_internet, speed = 2.0f)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = ingredientsState.error,
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 70.dp, lottieFile = R.raw.loader, speed = 2.0f)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Please wait..",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        }
    )
}