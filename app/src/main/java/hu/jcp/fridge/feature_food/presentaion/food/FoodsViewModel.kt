package hu.jcp.fridge.feature_food.presentaion.food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.use_case.FoodUseCases
import hu.jcp.fridge.feature_food.domain.util.FoodOrder
import hu.jcp.fridge.feature_food.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsViewModel @Inject constructor(
    private val foodUseCases: FoodUseCases
) : ViewModel() {

    private val _state = mutableStateOf(FoodState())
    val state: State<FoodState> = _state

    private var recentlyDeleteFood: Food? = null

    private var getFoodsJob: Job? = null

    init {
        getFoods(FoodOrder.Title(OrderType.Ascending))
    }

    fun onEvent(event: FoodsEvent) {
        when (event) {
            is FoodsEvent.Order -> {
                if (state.value.foodOrder::class == event.foodOrder::class &&
                    state.value.foodOrder.orderType == event.foodOrder.orderType
                ) {
                    return
                }
                getFoods(event.foodOrder)
            }
            is FoodsEvent.RestoreFood -> {
                viewModelScope.launch {
                    foodUseCases.addFood(recentlyDeleteFood ?: return@launch)
                    recentlyDeleteFood = null
                }
            }
            is FoodsEvent.DeleteFood -> {
                viewModelScope.launch {
                    foodUseCases.deleteFood(event.food)
                    recentlyDeleteFood = event.food
                }
            }
            is FoodsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getFoods(foodOrder: FoodOrder) {
        getFoodsJob?.cancel()
        foodUseCases.getFoods(foodOrder)
            .onEach { foods ->
                _state.value = state.value.copy(
                    foods = foods,
                    foodOrder = foodOrder
                )
            }
            .launchIn(viewModelScope)
    }
}