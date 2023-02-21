package com.fudie.core.screens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import color_primary_light
import com.fudie.core.R
import com.fudie.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, iconVisible: Boolean = false, title: String) {
    MaterialTheme.colorScheme.background
    Column {
        val boxBackground = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            navigationIcon = {
                if (iconVisible) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navController.navigateUp()
                            }
                            .clip(CircleShape)
                            .size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        tint = boxBackground
                    )
                }
            },

            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            )
        )
    }
}