package hu.jcp.fridge.feature_food.domain.util

sealed class FoodOrder(val orderType: OrderType){
    class Title(orderType: OrderType) : FoodOrder(orderType)

    fun copy(orderType: OrderType) : FoodOrder {
        return when(this){
            is Title -> Title(orderType)
        }
    }
}
