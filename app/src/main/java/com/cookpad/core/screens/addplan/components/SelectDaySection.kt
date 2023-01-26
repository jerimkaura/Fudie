package com.cookpad.core.screens.addplan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import color_box_border_dark
import color_box_border_light
import color_box_light
import color_primary_dark
import color_surface_dark
import com.cookpad.core.screens.addplan.AddPlanViewModel
import com.cookpad.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDaySection(addPlanViewModel: AddPlanViewModel = hiltViewModel()) {
    var mExpanded by remember { mutableStateOf(false) }

    val days = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    val selectedDay = addPlanViewModel.selectedDay.value
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {
        val boxColor = if (isSystemInDarkTheme()) color_surface_dark else color_box_light
        val borderColor =
            if (isSystemInDarkTheme()) color_box_border_dark else color_box_border_light
        Text(
            text = "Select Day of the week", style = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            textStyle = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ),
            value = selectedDay,
            onValueChange = { addPlanViewModel.updateSelectedDay(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = boxColor,
                unfocusedIndicatorColor = borderColor,
                focusedIndicatorColor = borderColor
            )
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                .background(boxColor)
        ) {
            days.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        addPlanViewModel.updateSelectedDay(label)
                        mExpanded = false
                    }, text = {
                        Text(
                            text = label, style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                            )
                        )
                    }
                )
            }
        }
    }
}