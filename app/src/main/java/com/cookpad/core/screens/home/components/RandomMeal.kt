package com.cookpad.core.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.ui.theme.montserrat

@Composable
fun RandomMeal(randomRecipe: RecipeState, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        if (randomRecipe.error.isNotEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
//                    .background(Color(0xff000000).copy(0.2f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 100.dp, lottieFile = R.raw.no_connection, speed = 1.0f)
                    Text(
                        modifier = Modifier,
                        text = randomRecipe.error.toString(),
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                    )
                }

            }
        }
        if (randomRecipe.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                LottieAnime(size = 30.dp, lottieFile = R.raw.loader, speed = 1.0f)
            }

        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xff000000).copy(0.1f))
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            randomRecipe.data?.strMealThumb
                                ?: "https://www.themealdb.com/images/media/meals/xrttsx1487339558.jpg"
                        )
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                        .build(),
                    loading = {

                    },
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = randomRecipe.data?.strMeal ?: "",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = Color.White
                        )

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onClick.invoke()
                        }) {
                        Text(
                            text = "GET A RANDOM RECIPE",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}