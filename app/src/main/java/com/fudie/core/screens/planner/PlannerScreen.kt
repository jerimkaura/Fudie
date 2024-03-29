package com.fudie.core.screens.planner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fudie.core.navigation.Route
import com.fudie.core.screens.planner.components.DayMealsSection
import com.fudie.core.screens.planner.components.DaysOfTheWeekSection
import com.fudie.core.screens.planner.components.TopBarPlannerScreen
import com.fudie.core.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    navController: NavController,
    plannerViewModel: PlannerViewModel = hiltViewModel()
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 70.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                content = {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_add),
                            contentDescription = null,
                        )
                    }
                },
                onClick = {
                    navController.navigate(Route.AddPlanScreen.route)
                }
            )

        },
        topBar = {
            TopBarPlannerScreen()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding() + 60.dp,
                        top = paddingValues.calculateTopPadding(),
                    )
            ) {
                DaysOfTheWeekSection(plannerViewModel)
                DayMealsSection(plannerViewModel)
            }
        }
    )
}




