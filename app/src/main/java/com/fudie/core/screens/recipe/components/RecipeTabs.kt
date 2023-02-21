package com.fudie.core.screens.recipe.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import color_primary_light
import com.fudie.core.screens.utils.Constants
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.Recipe
import com.fudie.domain.model.RecipeDetail
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<MealTabs>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    androidx.compose.material.TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .height(1.dp),
                color = color_primary_light,
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = "", tint = color_primary_light
                    )
                },
                text = {
                    Text(
                        text = tab.title,
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                        ),
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<MealTabs>, pagerState: PagerState, recipe: Recipe) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        when (tabs[page].title) {
            Constants.INGREDIENTS -> {
                RecipeIngredients(recipe)
            }
            Constants.INSTRUCTIONS -> {
                RecipeInstructions(recipe)
            }
        }
    }
}

@Composable
fun RecipeInstructions(recipe: Recipe) {
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val context = LocalContext.current
        if (!recipe.strYoutube.contains("No value")) {
            Button(
                modifier = Modifier.padding(vertical = 5.dp),
                onClick = {
                    Toast.makeText(context, "Streaming coming soon!", Toast.LENGTH_LONG).show()
                }
            ) {
                Text(
                    text = "Watch Video",
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                )
            }
        }
        Text(
            modifier = Modifier.verticalScroll(scroll),
            text = recipe.strInstructions,
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 2.5.em,
                textAlign = TextAlign.Justify,
            )
        )

    }

}


@Composable
fun RecipeIngredients(recipe: Recipe) {
    val recipeIngredients = recipe.toIngredientItem()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        items(recipeIngredients) { recipeIngredient ->
            IngredientItem(recipeIngredient)
        }
    }
}

@Composable
fun DetailItem(detail: RecipeDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            )
            {

                Text(
                    text = detail.key,
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = detail.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        letterSpacing = (0.8).sp,
                        color = Color.Gray
                    )
                )

            }
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )

        }

    }
}


