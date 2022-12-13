package com.cookpad.core.screens.category

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.R
import com.cookpad.core.screens.category.components.CategoryDialog
import com.cookpad.core.screens.category.components.CategoryItem
import com.cookpad.core.screens.category.components.TopBarCategoriesScreen
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.utils.LottieAnime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {
    val mealCategories = viewModel.mealCategories.value
    val openDialog = remember { mutableStateOf(false) }
    val index = remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopBarCategoriesScreen()
        },
        content = { paddingValues ->
            if (mealCategories.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 180.dp, lottieFile = R.raw.veggies, speed = 0.50f)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Hang on chef...")

                }
            } else if (mealCategories.error.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(size = 180.dp, lottieFile = R.raw.veggies, speed = 2.0f)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Hang on chehjf...")


                }

            } else {
                val categories = mealCategories.data ?: emptyList()
                if (categories.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(
                                bottom = paddingValues.calculateBottomPadding(),
                                top = paddingValues.calculateTopPadding()
                            )
                            .fillMaxSize(), columns = GridCells.Fixed(3)
                    ) {
                        items(categories.size) { category ->
                            CategoryItem(categories[category], navController, onClick = {
                                index.value = categories.indexOf(it)
                            }, openDialog)
                        }
                    }
                }
            }
        }
    )
    if (openDialog.value) {
        CategoryDialog(openDialog, mealCategories.data!![index.value], navController)
    }
}




