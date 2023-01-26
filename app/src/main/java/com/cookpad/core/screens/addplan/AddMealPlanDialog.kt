package com.cookpad.core.screens.addplan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import color_primary_light
import color_surface_dark
import color_surface_light
import com.cookpad.core.R
import com.cookpad.core.screens.addplan.components.SearchAppBar
import com.cookpad.core.screens.addplan.states.SearchRecipeState
import com.cookpad.core.screens.addplan.states.SelectedMealsState
import com.cookpad.core.screens.planner.PlannerViewModel
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Meal
import kotlinx.coroutines.launch

@Composable
fun AddMealPlanDialog(
    openDialog: MutableState<Boolean>,
    selectedMealsState: SelectedMealsState,
    addPlanViewModel: AddPlanViewModel = hiltViewModel(),
    mealPlanViewModel: PlannerViewModel = hiltViewModel()
) {
    val text = addPlanViewModel.searchTextState.value
    val dialogBackground = if (isSystemInDarkTheme()) color_surface_dark else color_surface_light
    val scroll = rememberScrollState(0)
    val dialogWidth = (LocalConfiguration.current.screenWidthDp - 40).dp
    val height = (LocalConfiguration.current.screenHeightDp - 500).dp
    val searchResult =
        addPlanViewModel.searchRecipeState.collectAsState(initial = SearchRecipeState()).value
    val scope = rememberCoroutineScope()
    val cxt = LocalContext.current
    Dialog(
        content = {
            Column(
                modifier = Modifier
                    .padding()
                    .background(dialogBackground, RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Search Meal", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                SearchAppBar(
                    text = text,
                    onTextChange = {
                        addPlanViewModel.updateSearchTextState(it)
                        addPlanViewModel.searchMeal(it)
                    },
                    onCloseClicked = { /*TODO*/ },
                    onSearchClicked = {

                    }
                )
                val searchedRecipes = searchResult.data ?: emptyList()
                LazyColumn(
                    modifier = Modifier
                        .height(height)
                        .width(dialogWidth),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    searchedRecipes.forEach {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = CardDefaults.cardElevation(1.dp)
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(it.strMealThumb)
                                            .crossfade(true).diskCachePolicy(CachePolicy.ENABLED)
                                            .build(),
                                        contentDescription = stringResource(R.string.app_name),
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier.weight(0.9f),
                                        text = it.strMeal,
                                        style = TextStyle(
                                            fontFamily = montserrat,
                                            fontSize = 12.sp,
                                            letterSpacing = (0.8).sp,
                                            color = Color.Gray,
                                        ),
                                        maxLines = 2,
                                        overflow = TextOverflow.Clip
                                    )

                                    Icon(
                                        modifier = Modifier
                                            .weight(0.1f)
                                            .clickable {
                                                scope.launch {
                                                    val meal =
                                                        Meal(it.idMeal, it.strMeal, it.strMealThumb)
                                                    addPlanViewModel.addToListOfMealsToAddToPlan(
                                                        meal
                                                    )
                                                    addPlanViewModel.updateSearchTextState("")
                                                    openDialog.value = false
                                                }
                                            },
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle_add),
                                        contentDescription = "",
                                        tint = color_primary_light
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        onDismissRequest = {
            openDialog.value = false
        }
    )
}