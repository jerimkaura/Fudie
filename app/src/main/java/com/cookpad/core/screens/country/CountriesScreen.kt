package com.cookpad.core.screens.country

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.home.states.CountriesState
import com.cookpad.core.screens.home.states.MealsState
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    countriesViewModel: CountriesViewModel = hiltViewModel()
) {
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
    val countries = homeViewModel.countries.value
    val meals = countriesViewModel.meals.value
    val selectedCountry: MutableState<Country?> = remember {
        mutableStateOf(null)
    }

    Scaffold(topBar = {}) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                )
                .fillMaxSize()
        ) {
            if (selectedCountry.value == null) {
                if (countries.error.isEmpty() && !countries.isLoading) {
                    selectedCountry.value = countries.data!![0]
                }
            }

            SelectedCountry(selectedCountry)
            CountriesSection(countries, countriesViewModel, selectedCountry)
            CountryMealsSection(meals, navController)
        }
    }
}

@Composable
fun CountriesSection(
    countries: CountriesState,
    countriesViewModel: CountriesViewModel,
    selectedCountry: MutableState<Country?>
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int -> selectedIndex = index }
    LazyRow(
        modifier = Modifier.wrapContentHeight(),
    ) {
        if (countries.error.isEmpty() && !countries.isLoading) {
            items(countries.data!!.size) { index ->
                CountryItem(countries.data ?: listOf(),
                    index = index,
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = countries.data!!.indexOf(it)
                        countriesViewModel.getMealByCountryNameName(it.strArea)
                        selectedCountry.value = it
                    }
                )
            }
        }
    }
}


@Composable
fun SelectedCountry(
    selectedCountry: MutableState<Country?>
) {
    val iconTint = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val boxBackground = if (isSystemInDarkTheme()) Color(0XFF2F2E41) else color_surface_light

    Column {
        Box(
            Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .height(200.dp)
                .background(Color.Blue.copy(0.1f))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedCountry.value?.flagUrl)
                    .crossfade(true).diskCachePolicy(CachePolicy.ENABLED).build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff000000).copy(alpha = 0.30F))
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

            }
            Button(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = {

                }) {
                Text(
                    text = "TRY ${selectedCountry.value?.strArea?.uppercase()}", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionHeader(heading = "Have fun selecting", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CountryItem(
    countries: List<Country>, index: Int, selected: Boolean, onClick: (country: Country) -> Unit
) {
    val textColor = if (isSystemInDarkTheme()) {
        if (selected) {
            color_surface_light
        } else {
            color_surface_light
        }
    } else {
        if (selected) {
            color_surface_light
        } else {
            color_surface_dark
        }
    }
    val backGroundColor = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(5.dp)
            .wrapContentHeight()
    ) {
        Box(contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(if (selected) color_primary_light else backGroundColor)
                .clickable {
                    onClick.invoke(countries[index])
                }) {
            Text(
                text = countries[index].strArea,
                Modifier
                    .padding(1.dp)
                    .padding(10.dp),
                style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


@Composable
fun CountryMealsSection(
    mealsState: MealsState, navController: NavController
) {
    val itemBgColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_primary_light
    val badgeColor =
        if (isSystemInDarkTheme()) color_surface_dark.copy(0.6f) else color_surface_light
    if (!mealsState.isLoading && mealsState.error.isEmpty()) {
        val meals = mealsState.data ?: listOf()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(meals.size) {
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
                            navController.navigate(
                                Route.RecipeScreen.route + "/${meals[it].idMeal}"
                            )
                        }
                        .wrapContentSize(),
                        shape = RoundedCornerShape(5.dp),
                        elevation = CardDefaults.cardElevation(1.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(meals[it].strMealThumb).crossfade(true)
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
                        text = meals[it].strMeal,
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
            }
        }
    }
}