package com.fudie.core.screens.ingredient.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_light
import com.fudie.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarIngredientsScreen() {
    if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val boxBackground = MaterialTheme.colorScheme.background
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            title = {
                Text(
                    text = "Recipe Ingredients", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            )
        )
    }
}
