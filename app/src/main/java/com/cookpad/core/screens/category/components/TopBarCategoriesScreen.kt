package com.cookpad.core.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_primary_light
import com.cookpad.core.R
import com.cookpad.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCategoriesScreen() {
    val iconTint = if (isSystemInDarkTheme()) color_primary_light else color_primary_light
    val boxBackground = MaterialTheme.colorScheme.background
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp),
            title = {
                Text(
                    text = "Recipe Categories", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            navigationIcon = {
//                Box(
//                    modifier = Modifier
//                        .padding(vertical = 0.dp)
//                        .background(boxBackground, CircleShape)
//                        .size(30.dp)
//                        .clip(CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        modifier = Modifier
//                            .size(20.dp),
//                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
//                        contentDescription = "Notification Icon",
//                        tint = Color.Gray
//                    )
//
//                }
            },
            actions = {

            },
        )
    }
}
