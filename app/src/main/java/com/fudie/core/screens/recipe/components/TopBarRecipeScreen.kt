package com.fudie.core.screens.recipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.fudie.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarRecipeScreen() {
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 0.dp),
            title = {

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clip(CircleShape)
                        .size(45.dp)
                        .clickable {  },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            },
            actions = {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Green,
                                contentColor = Color.White
                            ) {
                                Text(
                                    text = "100",
                                    color = Color.White
                                )
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ingredients),
                                contentDescription = "Notification Icon",
                            )
                        }
                    }
                }
            },
        )
    }
}