package com.fudie.core.screens.addplan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import color_primary_light
import com.fudie.core.ui.theme.montserrat
import com.fudie.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TopBarAddPlannerScreen(navController: NavController) {
    val iconColor = if (isSystemInDarkTheme()) Color.Gray else color_primary_light
    Column {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(
                    modifier = Modifier
                        .background(Color.Transparent, CircleShape)
                        .size(30.dp)
                        .clip(CircleShape),
                    onClick = {
                        navController.popBackStack()
                        navController.navigateUp()
                    }) {

                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Search Icon",
                        tint = iconColor
                    )
                }
            },
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            title = {
                Text(
                    text = "New Plan", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            actions = {

            }
        )
    }
}