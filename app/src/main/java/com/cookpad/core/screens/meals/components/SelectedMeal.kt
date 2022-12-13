package com.cookpad.core.screens.meals.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SelectedMeal(meals: List<Meal>?, navController: NavController) {
    val iconTint = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val boxBackground = if (isSystemInDarkTheme()) Color(0XFF2F2E41) else color_surface_light
    val meal: MutableState<Meal?> = remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        while (true) {
            meals?.isNotEmpty()?.let {
                val random = meals.random()
                meal.value = random
            }


            delay(5.seconds)
        }
    }

    Column() {
        Box(
            Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .height(250.dp)
                .background(Color(0xff000000).copy(0.2f))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.value?.strMealThumb)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(vertical = 36.dp, horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                            .clickable {
                                navController.navigateUp()
                            }
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

            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                text = meal.value?.strMeal ?: "",
                style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = Color.White, textAlign = TextAlign.Center
                )

            )

            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {

                }) {
                Text(
                    text = "TRY OUR SELECTION",
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionHeader(heading = "Have fun selecting", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}
