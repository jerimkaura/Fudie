package com.fudie.core.screens.recipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_light
import com.fudie.core.ui.theme.montserrat
import com.fudie.domain.model.IngredientItem
import com.fudie.core.R
@Composable
fun IngredientItem(recipeIngredient: IngredientItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(15.dp),
            painter = painterResource(id = R.drawable.ic_circle_tick),
            contentDescription = "", tint = color_primary_light,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = recipeIngredient.ingredientName + " - "+ recipeIngredient.ingredientQuantity, style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        )
    }
}
