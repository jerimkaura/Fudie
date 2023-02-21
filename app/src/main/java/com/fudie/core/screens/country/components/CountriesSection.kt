package com.fudie.core.screens.country.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_light
import color_surface_dark
import color_surface_light
import com.fudie.core.screens.country.CountriesViewModel
import com.fudie.core.screens.home.states.CountriesState
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.Country
import kotlin.math.log

@Composable
fun CountriesSection(
    countries: CountriesState,
    countriesViewModel: CountriesViewModel,
    selectedCountry: MutableState<Country?>
) {
    var selectedIndex by remember { mutableStateOf(0) }

    LazyRow(
        modifier = Modifier.wrapContentHeight(),
    ) {
        if (countries.isLoading) {
            item {
                Text(text = "Loading")
            }
        } else if (countries.error.isNotEmpty()) {
            item {
                Text(text = countries.error)
            }
        } else {
            selectedIndex = countries.data?.indexOf(selectedCountry.value) ?: 0
            items(countries.data?.size ?: 0) { index ->
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