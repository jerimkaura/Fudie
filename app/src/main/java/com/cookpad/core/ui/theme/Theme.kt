package com.cookpad.core.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import color_background_dark
import color_background_light
import color_on_background_dark
import color_on_background_light
import color_on_primary_dark
import color_on_primary_light
import color_primary_dark
import color_primary_light
import color_surface_dark
import color_surface_light


private val DarkColorPalette = darkColorScheme(
    primary = color_primary_dark,
    background = color_background_dark,
    surface = color_surface_dark,
    onPrimary = color_on_primary_dark,
    onBackground = color_on_background_dark,
)

private val LightColorPalette = lightColorScheme(
    primary = color_primary_light,
    background = color_background_light,
    surface = color_surface_light,
    onPrimary = color_on_primary_light,
    onBackground = color_on_background_light,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CookPadThem(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (!useDarkTheme) {
      LightColorPalette
  } else {
      DarkColorPalette
  }

  MaterialTheme(
    colorScheme = colors,
    content = content
  )
}