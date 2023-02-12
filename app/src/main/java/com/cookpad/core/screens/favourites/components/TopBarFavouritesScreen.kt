package com.cookpad.core.screens.favourites.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_light
import com.cookpad.core.screens.utils.BoxIcon
import com.cookpad.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFavouritesScreen() {
    if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            title = {
                Text(
                    text = "Favorites", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            actions = {
                BoxIcon(
                    icon = Icons.Default.MoreVert,
                    onClick = {},
                    boxBackground = Color.Transparent
                )
            }
        )
    }
}
