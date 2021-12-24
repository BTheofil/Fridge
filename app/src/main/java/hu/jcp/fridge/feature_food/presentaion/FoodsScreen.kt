package hu.jcp.fridge.feature_food.presentaion

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.jcp.fridge.feature_food.presentaion.food.FoodsEvent
import hu.jcp.fridge.feature_food.presentaion.food.FoodsViewModel
import hu.jcp.fridge.feature_food.presentaion.food.components.FoodItem
import hu.jcp.fridge.feature_food.presentaion.food.components.OrderSection
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun FoodScreen(navController: NavController, viewModel: FoodsViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add food")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your note",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(FoodsEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                    foodOrder = state.foodOrder,
                    onOrderChange = {
                        viewModel.onEvent(FoodsEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.foods) { food ->
                    FoodItem(food = food, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {},
                        onDeleteClick = {
                            viewModel.onEvent(FoodsEvent.DeleteFood(food))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Food deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(FoodsEvent.RestoreFood)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}