package hu.jcp.fridge.feature_food.presentaion.food

import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.util.FoodOrder
import hu.jcp.fridge.feature_food.domain.util.OrderType

data class FoodState(
    val foods: List<Food> = emptyList(),
    val foodOrder: FoodOrder = FoodOrder.Title(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false,
)
