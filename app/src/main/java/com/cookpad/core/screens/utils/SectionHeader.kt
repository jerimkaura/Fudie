package com.cookpad.core.screens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookpad.core.ui.theme.montserrat


@Composable
fun SectionHeader(heading: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = heading,
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )


        )
        Text(
            modifier = Modifier.clickable { onClick.invoke() },
            text = "See All",
            style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        )
    }

}
