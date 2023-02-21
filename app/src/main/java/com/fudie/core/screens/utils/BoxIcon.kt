package com.fudie.core.screens.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import color_primary_light
import color_surface_light

@Composable
fun BoxIcon(icon: ImageVector, onClick: () -> Unit, boxBackground: Color) {
    val iconTint = color_surface_light
    Box(
        modifier = Modifier
            .padding(vertical = 0.dp)
            .background(boxBackground, CircleShape)
            .size(30.dp)
            .clip(CircleShape)
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            imageVector = icon,
            contentDescription = "",
            tint = iconTint
        )
    }
}