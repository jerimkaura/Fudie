package com.cookpad.core.navigation.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cookpad.core.ui.theme.montserrat

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Categories,
        BottomNavItem.Countries,
        BottomNavItem.Ingredients,
        BottomNavItem.Favourites,
    )
    NavigationBar(
        modifier = Modifier.wrapContentHeight(),
        tonalElevation = 1.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.icon),
                                contentDescription = item.title
                            )
                            if (selected) {
                                Text(
                                    text = item.title,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontFamily = montserrat,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
                                    )
                                )
                            }
                        }
                    },
                )
            }
        }
    }
