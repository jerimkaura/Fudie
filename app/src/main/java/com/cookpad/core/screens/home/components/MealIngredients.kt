package com.cookpad.core.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cookpad.core.R
import com.cookpad.domain.model.Ingredient

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealIngredients(ingredients: List<Ingredient>) {
    val boxColor = if (isSystemInDarkTheme()) Color(0xFF1e2025) else Color(0xFFF8F6F8)
    val chipBorder = if (isSystemInDarkTheme()) Color(0XFF2F2E41) else Color(0xFFfed69a)
    LazyHorizontalStaggeredGrid(
        modifier = Modifier.height(180.dp),
        rows = StaggeredGridCells.Fixed(5),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(ingredients.size) {
            AssistChip(
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = chipBorder
                ),
                shape = RoundedCornerShape(20.dp),
                onClick = { /* Do something! */ },
                label = { Text(text = ingredients[it].strIngredient) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trending),
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = boxColor,
                )
            )
        }
    }
}