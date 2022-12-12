package com.cookpad.core.screens.category

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.home.LoadingAnimation
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.MealCategory

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
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = paddingValues.calculateTopPadding()
                    )
                    .fillMaxSize(), columns = GridCells.Fixed(3)
            ) {
                if (mealCategories.isLoading) {
                    item {
                        LoadingAnimation(height = 120.dp)
                    }
                } else {
                    items(mealCategories.data!!.size) { category ->
                        CategoryItem(mealCategories.data!![category], navController, onClick = {
                            index.value = mealCategories.data!!.indexOf(it)

                        }, openDialog)

                    }
                }
            }

        }
    )
    if (openDialog.value) {
        CategoryDialog(openDialog, mealCategories.data!![index.value], navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCategoriesScreen() {
    val iconTint = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val boxBackground = MaterialTheme.colorScheme.background
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            title = {
                Text(
                    text = "Recipe Categories", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            navigationIcon = {
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
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Notification Icon",
                        tint = Gray
                    )

                }
            },
            actions = {

            },
        )
    }
}


@Composable
fun CategoryItem(
    category: MealCategory,
    navController: NavController,
    onClick: (mealCategory: MealCategory) -> Unit,
    openDialog: MutableState<Boolean>
) {
    val itemBgColor = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 0.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    openDialog.value = true
                    onClick.invoke(category)
                }
                .background(itemBgColor, RoundedCornerShape(10.dp))
                .size(120.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.strCategoryThumb)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                    .build(),
                placeholder = painterResource(R.drawable.lily),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = category.strCategory, style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CategoryDialog(
    openDialog: MutableState<Boolean>,
    mealCategory: MealCategory,
    navController: NavController
) {
    val dialogBackground = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    val context = LocalContext.current
    val scroll = rememberScrollState(0)
    AlertDialog(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(400.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(dialogBackground),
        onDismissRequest = { openDialog.value = false },
        title = {
            Text(
                text = mealCategory.strCategory, style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            )
        },

        text = {
            Column(
                modifier = Modifier
                    .height(699.dp)
            ) {
                Text(
                    text = mealCategory.strCategoryDescription,
                    modifier = Modifier.verticalScroll(scroll),
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 2.0.em,
                        textAlign = TextAlign.Justify,
                    )
                )
            }

        },
        confirmButton = {
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    navController.navigate(
                        Route
                            .MealsScreen
                            .route + "/${mealCategory.strCategory}"
                    )
                    openDialog.value = false
                }
            ) {
                Text("View Meals")
            }
        },
        dismissButton = {
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Dismiss")
            }
        },
        containerColor = dialogBackground
    )
}