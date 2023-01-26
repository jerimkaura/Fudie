package com.cookpad.core.screens.addplan.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_box_border_dark
import color_box_border_light
import color_box_light
import color_surface_dark
import com.cookpad.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val bgColor = if (isSystemInDarkTheme()) {
        Color(0XFF2F2E41)
    } else {
        Color(0xFFF8F6F8)
    }

    val iconTint = if (isSystemInDarkTheme()) {
        Green
    } else {
        Color(0XFF2F2E41)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val boxColor = if (isSystemInDarkTheme()) color_surface_dark else color_box_light
        val borderColor =
            if (isSystemInDarkTheme()) color_box_border_dark else color_box_border_light

        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Search...", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp, color = Gray
                    )
                )
            },
            modifier = Modifier
                .height(50.dp)
                .padding(0.dp),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked(text)
            }),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = boxColor,
                unfocusedIndicatorColor = borderColor,
                focusedIndicatorColor = borderColor
            )
        )

        Text(
            modifier = Modifier
                .clickable {
                    onSearchClicked.invoke(text)
                }
                .padding(end = 10.dp),
            text = "Search", style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        )
    }

}