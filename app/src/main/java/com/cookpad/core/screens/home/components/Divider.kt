package com.cookpad.core.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Divider
import color_surface_dark

@Composable
fun RowSpacer(){
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .background(
                if (isSystemInDarkTheme()) {
                    color_surface_dark
                } else {
                    Color(0xffEFEEEF)
                }
            )
    )
}
