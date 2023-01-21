package com.cookpad.core.screens.country

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.screens.country.components.CountriesSection
import com.cookpad.core.screens.country.components.CountryMealsSection
import com.cookpad.core.screens.country.components.SelectedCountry
import com.cookpad.core.screens.home.HomeViewModel
import com.cookpad.core.screens.recipe.RecipeViewModels
import com.cookpad.core.screens.recipe.states.RecipeState
import com.cookpad.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel()
) {
    val countries = homeViewModel.countries.value
    val meals = countriesViewModel.meals.value
    val recipeState: MutableState<RecipeState?> = remember {
        mutableStateOf(null)
    }

    recipeState.value = recipeViewModel.recipe.collectAsState().value

    val selectedCountry: MutableState<Country?> = remember {
        mutableStateOf(null)
    }

    val savedCountryName = countriesViewModel.selectedCountryName.value
<<<<<<< HEAD

=======
>>>>>>> main
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                    .fillMaxSize()
            ) {
                if (selectedCountry.value == null) {
                    if (countries.error.isEmpty() && !countries.isLoading) {
                        if (savedCountryName.isNotEmpty()) {
                            Log.d("SAVED COUNTRY 1", "CountriesScreen: $savedCountryName")
                            val tempCountry = countries.data?.find {
                                it.strArea == savedCountryName
                            }
                            Log.d("TEMP COUNTRY 1", "CountriesScreen: $tempCountry")
                            selectedCountry.value = tempCountry
                        } else {
                            selectedCountry.value = countries.data?.get(0)
                        }
                    }
                }
                SelectedCountry(selectedCountry)
                CountriesSection(countries, countriesViewModel, selectedCountry)
                CountryMealsSection(meals, recipeViewModel, navController)
            }
        }
    )
}
