package com.cookpad.core.screens.addplan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.cookpad.core.R

@Composable
fun EmptyPlanItem(onClick: () -> Unit) {
    val itemWidth = (((LocalConfiguration.current.screenWidthDp).toDouble() / 2) - 20).dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 5.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    onClick.invoke()
                }
                .width(itemWidth)
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff000000).copy(alpha = 0.30F), RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle_add),
                        contentDescription = "",
                        tint = if (isSystemInDarkTheme()) Color.Gray else Color.White
                    )
                }
            }
        }
    }
}