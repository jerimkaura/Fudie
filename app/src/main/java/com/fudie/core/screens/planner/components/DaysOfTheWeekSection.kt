package com.fudie.core.screens.planner.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_dark
import color_surface_dark
import color_surface_light
import com.fudie.core.screens.planner.PlannerViewModel
import com.fudie.core.screens.utils.getDay
import com.fudie.core.ui.theme.montserrat

@Composable
fun DaysOfTheWeekSection(plannerViewModel: PlannerViewModel) {
    val days = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )
    var selectedIndex by remember { mutableStateOf(days.indexOf(getDay())) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(days.size) { index ->
            DayItem(
                days[index],
                index,
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = days.indexOf(it)
                    plannerViewModel.getMealPlansByDayOfTheWeek(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayItem(
    day: String,
    index: Int,
    selected: Boolean,
    onClick: (dayName: String) -> Unit
) {
    val boxColor = if (isSystemInDarkTheme()) Color(0xFF1e2025) else Color(0xFFF8F6F8)
    val chipBorder = if (isSystemInDarkTheme()) Color(0XFF2F2E41) else Color(0xFFfed69a)
    val textColor = if (isSystemInDarkTheme()) {
        if (selected) {
            color_surface_light
        } else {
            color_surface_light
        }
    } else {
        if (selected) {
            color_surface_light
        } else {
            color_surface_dark
        }
    }
    AssistChip(
        modifier = Modifier.padding(horizontal = 3.dp),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = chipBorder
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            onClick.invoke(day)
        },
        label = {
            Text(
                text = day,
                style = TextStyle(
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = textColor
                )
            )
        },
        leadingIcon = {
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) color_primary_dark else boxColor,
        )
    )
}
