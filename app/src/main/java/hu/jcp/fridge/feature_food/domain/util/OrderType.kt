package hu.jcp.fridge.feature_food.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
