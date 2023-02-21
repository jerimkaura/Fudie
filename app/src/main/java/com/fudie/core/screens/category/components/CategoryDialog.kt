package com.fudie.core.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import color_surface_dark
import color_surface_light
import com.fudie.core.navigation.Route
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.MealCategory


@Composable
fun CategoryDialog(
    openDialog: MutableState<Boolean>,
    mealCategory: MealCategory,
    navController: NavController
) {
    val dialogBackground = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    val scroll = rememberScrollState(0)
    val dialogWidth = (LocalConfiguration.current.screenWidthDp - 40).dp
    AlertDialog(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(400.dp).width(dialogWidth)
            .clip(RoundedCornerShape(10.dp))
            .background(dialogBackground),
        onDismissRequest = { openDialog.value = false },
        title = {
            Text(
                text = mealCategory.strCategory, style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            )
        },

        text = {
            Column(
                modifier = Modifier
                    .height(699.dp)
            ) {
                Text(
                    text = mealCategory.strCategoryDescription,
                    modifier = Modifier.verticalScroll(scroll),
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 2.0.em,
                        textAlign = TextAlign.Justify,
                    )
                )
            }

        },
        confirmButton = {
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    navController.navigate(
                        Route
                            .MealsScreen
                            .route + "/${mealCategory.strCategory}"
                    )
                    openDialog.value = false
                }
            ) {
                Text("View Meals")
            }
        },
        dismissButton = {
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Dismiss")
            }
        },
        containerColor = dialogBackground
    )
}