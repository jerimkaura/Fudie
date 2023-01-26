package com.cookpad.core.screens.addplan.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cookpad.core.navigation.Route
import com.cookpad.core.screens.addplan.AddPlanViewModel
import com.cookpad.core.screens.addplan.states.SelectedMealsState
import com.cookpad.core.screens.planner.PlannerViewModel
import com.cookpad.core.screens.planner.states.AddPlanState
import com.cookpad.domain.model.MealPlan
import kotlinx.coroutines.launch

@Composable
fun AddMealSection(
    openDialog: MutableState<Boolean>,
    selectedMealState: SelectedMealsState,
    createMealPlanResponse: AddPlanState,
    navController: NavController,
    addPlanViewModel: AddPlanViewModel = hiltViewModel(),
    mealPlanViewModel: PlannerViewModel = hiltViewModel()
) {
    val listOfMeals = addPlanViewModel.listOfMealsToAddToPlan
    val context = LocalContext.current
    val selectedDay = addPlanViewModel.selectedDay.value
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .heightIn(300.dp, 500.dp)
                .padding(horizontal = 10.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(listOfMeals.size) { index ->
                PlanItem(listOfMeals[index], addPlanViewModel)
            }
            item {
                if (listOfMeals.size <= 3) {
                    EmptyPlanItem(
                        onClick = {
                            openDialog.value = true
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                scope.launch {
                    if (listOfMeals.isEmpty()) {
                        Toast.makeText(context, "No Meal Selected", Toast.LENGTH_SHORT).show()
                    } else {
                        val mealPlans = listOfMeals.map { meal ->
                            MealPlan(
                                strDayOfWeek = selectedDay,
                                strMealId = meal.idMeal,
                                strMeal = meal.strMeal,
                                strMealThumbnail = meal.strMealThumb
                            )
                        }
                        addPlanViewModel.createMealPlan(mealPlans)
                    }

                }
            }
        ) {
            if (createMealPlanResponse.isLoading) {
                Text(text = "Loading...")
            } else if (!createMealPlanResponse.data.isNullOrBlank()) {
                Toast.makeText(context, "Meal plan Created ofr $selectedDay", Toast.LENGTH_SHORT).show()
                Text(text = "Success")
                addPlanViewModel.cleaMealsList()
                navController.navigate(Route.PlannerScreen.route)
            } else if (createMealPlanResponse.error.isNotEmpty()) {
                Text(text = createMealPlanResponse.error.toString())
            } else {
                Text(text = "Create plan for $selectedDay")
            }

        }

    }
}