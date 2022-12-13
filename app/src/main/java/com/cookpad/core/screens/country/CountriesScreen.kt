package com.cookpad.core.screens.country

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.screens.country.components.CountriesSection
import com.cookpad.core.screens.country.components.CountryMealsSection
import com.cookpad.core.screens.country.components.SelectedCountry
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.core.screens.utils.getActivity
import com.cookpad.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel()
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
    val recipeState: MutableState<RecipeState?> = remember {
        mutableStateOf(null)
    }

    val recipe = recipeViewModel.recipe.collectAsState().value
    recipeState.value = recipeViewModel.recipe.collectAsState().value

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
            CountryMealsSection(meals, recipeViewModel, navController)
        }
    }
}









