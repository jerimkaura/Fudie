package com.cookpad.core.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.launch

@Composable
fun AllMealsItem(meal: Meal, recipeViewModel: RecipeViewModels, navController: NavController) {
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 5.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.strMealThumb).crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            recipeViewModel.getRecipeByMealId(mealId = meal.idMeal)
                            navController.navigate(
                                Route.RecipeScreen.route + "/${meal.idMeal}"
                            )
                        }
                    }
                    .width(190.dp)
                    .height(120.dp),
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = meal.strMeal,
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                lineHeight = 1.5.em,
                fontSize = 12.sp,
            ),
            maxLines = 2
        )
    }
}