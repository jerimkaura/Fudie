import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.utils.BoxIcon
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.launch

@Composable
fun MealItem(
    meal: Meal,
    recipeViewModel: RecipeViewModels,
    navController: NavController,
    itemWidth: Dp
) {
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 10.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(itemWidth)
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            recipeViewModel.getRecipeByMealId(mealId = meal.idMeal)
                            navController.navigate(
                                Route.RecipeScreen.route + "/${meal.idMeal}"
                            )
                        }
                    }
                    .wrapContentSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(1.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.strMealThumb).crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff000000).copy(alpha = 0.30F), RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent, CircleShape)
                            .size(30.dp)
                            .clip(CircleShape),
                        onClick = {

                        }) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_favourite_outline),
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    }
                }
                Text(
                    text = meal.strMeal,
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}