package com.cookpad.core.screens.planner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.screens.planner.PlannerViewModel
import com.cookpad.core.screens.planner.states.MealPlansState
import com.cookpad.core.screens.utils.BoxIcon
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.MealPlan

@Composable
fun DayMealsSection(plannerViewModel: PlannerViewModel) {
    val mealPlanState by plannerViewModel.mealPlans.collectAsState(initial = MealPlansState())
    if (!mealPlanState.isLoading) {
        val mealPlans = mealPlanState.data ?: emptyList()
        if (mealPlans.isNotEmpty()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(mealPlans.size) { index ->
                    MealPlanItem(mealPlans[index])
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnime(size = 150.dp, lottieFile = R.raw.empty_list, speed = 2.5f)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "No Plans")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnime(size = 70.dp, lottieFile = R.raw.loader, speed = 2.5f)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Hang on chef...")
        }
    }

}

@Composable
fun MealPlanItem(mealPlan: MealPlan) {
    val itemWidth = (((LocalConfiguration.current.screenWidthDp).toDouble() / 2) - 20).dp
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
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mealPlan.strMealThumbnail)
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
                    BoxIcon(
                        icon = Icons.Default.MoreVert,
                        onClick = {},
                        boxBackground = Color.Transparent
                    )
                }
                Text(
                    text = mealPlan.strMeal,
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

