package com.cookpad.core.screens.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cookpad.core.R
import com.cookpad.core.screens.recipe.components.MealImage
import com.cookpad.core.screens.recipe.components.MealTabs
import com.cookpad.core.screens.recipe.components.Tabs
import com.cookpad.core.screens.recipe.components.TabsContent
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.core.screens.utils.LottieAnime
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Recipe
import com.google.accompanist.pager.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController, viewModel: RecipeViewModels = hiltViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val activity = context.getActivity()
        val window = activity?.window
        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        val windowInsetsController =
            window?.let { ViewCompat.getWindowInsetsController(it.decorView) }

        windowInsetsController?.isAppearanceLightNavigationBars = true

    }

    val recipe: RecipeState by viewModel.recipe.collectAsState(initial = RecipeState())

    Scaffold(
        topBar = {
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                    .fillMaxSize()
            ) {
                if (recipe.data != null) {
                    MealImage(recipe = recipe.data, navController)
                    RecipeDetails(recipe = recipe.data, navController = navController)
                } else if (recipe.isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LottieAnime(size = 350.dp, lottieFile = R.raw.hamburger, speed = 2.0f)
                        Text(
                            text = "Fetching your recipe...",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            ),
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 50.dp),
                            text = "This recipe is not available locally  Connect to the internet to fetch it!",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp, textAlign = TextAlign.Center,
                                lineHeight = 2.0.em
                            ),
                        )
                        Spacer(modifier = Modifier.height(70.dp))
                        LottieAnime(size = 150.dp, lottieFile = R.raw.no_internet, speed = 1.0f)
                        Spacer(modifier = Modifier.height(300.dp))
                        Button(
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier.wrapContentSize(),
                            onClick = { navController.navigateUp() }) {
                            Text(
                                text = "BACK",
                                style = TextStyle(
                                    fontFamily = montserrat,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                ),
                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeDetails(recipe: Recipe?, navController: NavController) {
    val tabs = listOf(
        MealTabs.Ingredients,
        MealTabs.Instructions,
    )
    val pagerState = rememberPagerState()
    recipe?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = it.strMeal,
                style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, it)
        }
    }

}

