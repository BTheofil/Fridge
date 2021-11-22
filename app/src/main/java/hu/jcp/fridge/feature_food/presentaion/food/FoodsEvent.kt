package hu.jcp.fridge.feature_food.presentaion.food

import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.util.FoodOrder

sealed class FoodsEvent{
    data class Order(val foodOrder: FoodOrder): FoodsEvent()
    data class DeleteFood(val food: Food): FoodsEvent()
    object RestoreFood: FoodsEvent()
    object ToggleOrderSection: FoodsEvent()
}
