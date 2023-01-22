package com.cookpad.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cookpad.core.R

val montserrat = FontFamily(
    Font(R.font.montserrat_bold,FontWeight.W600),
    Font(R.font.montserrat_medium,FontWeight.W400),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_thin, FontWeight.W300),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)