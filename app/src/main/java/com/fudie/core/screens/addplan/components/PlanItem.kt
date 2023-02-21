package com.fudie.core.screens.addplan.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fudie.core.R
import com.fudie.core.screens.addplan.AddPlanViewModel
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.Meal

@Composable
fun PlanItem(meal: Meal, addPlanViewModel: AddPlanViewModel) {
    val itemWidth = (((LocalConfiguration.current.screenWidthDp).toDouble() / 2) - 20).dp
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 5.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(itemWidth)
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.strMealThumb)
                        .crossfade(true).diskCachePolicy(CachePolicy.ENABLED).build(),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
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

                    Icon(
                        modifier = Modifier
                            .clickable {
                                addPlanViewModel.removeFromListOfMealsToAddToPlan(meal)
                                Toast
                                    .makeText(context, "Item Removed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                        contentDescription = "",
                        tint = Color.White
                    )
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