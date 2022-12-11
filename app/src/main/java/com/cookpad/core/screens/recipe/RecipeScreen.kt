package com.cookpad.core.screens.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.screens.recipe.components.MealImage
import com.cookpad.core.screens.recipe.components.MealTabs
import com.cookpad.core.screens.recipe.components.Tabs
import com.cookpad.core.screens.recipe.components.TabsContent
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

    val recipe = viewModel.recipe.value

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
                if (!recipe.isLoading) {
                    MealImage(recipe.data, navController)
                }

                if (!recipe.isLoading) {
                    RecipeDetails(recipe.data, navController)
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
        MealTabs.Details
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = recipe!!.strMeal,
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState, recipe)
    }
}
