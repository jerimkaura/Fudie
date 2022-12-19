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
import androidx.compose.ui.text.style.TextOverflow
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
fun MealItem(meal: Meal, recipeViewModel: RecipeViewModels, navController: NavController) {
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 0.dp)
            .width(140.dp)
            .height(120.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier
            .clickable {
                scope.launch {
                    recipeViewModel.getRecipeByMealId(mealId = meal.idMeal)
                    navController.navigate(
                        Route.RecipeScreen.route + "/${meal.idMeal}"
                    )
                }
            }
            .wrapContentSize(),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(1.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.strMealThumb).crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(130.dp)
                    .height(90.dp),
            )
        }

        Text(
            modifier = Modifier.padding(5.dp),
            text = meal.strMeal,
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 0.8.em,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
//    Column(
//        modifier = Modifier
//            .padding(3.dp)
//            .width(200.dp)
//            .height(150.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            contentAlignment = Alignment.BottomCenter
//        ) {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(meal.strMealThumb)
//                    .crossfade(true)
//                    .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
//                    .build(),
//                placeholder = painterResource(R.drawable.lily),
//                contentDescription = stringResource(R.string.app_name),
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clickable {
//                        navController.navigate(
//                            Route
//                                .RecipeScreen
//                                .route + "/${meal.idMeal}"
//                        )
//                    }
//                    .clip(RoundedCornerShape(15.dp))
//                    .fillMaxSize()
//            )
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(15.dp))
//                    .background(Color(0xff000000).copy(alpha = 0.40F))
//                    .padding(horizontal = 15.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Text(
//                    modifier = Modifier.padding(vertical = 10.dp),
//                    text = meal.strMeal,
//                    style = TextStyle(
//                        fontFamily = montserrat,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp,
//                        color = Color.White
//                    )
//                )
//
//            }
//        }
//    }
}