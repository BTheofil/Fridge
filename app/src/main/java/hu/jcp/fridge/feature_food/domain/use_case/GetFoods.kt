package hu.jcp.fridge.feature_food.domain.use_case

import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository
import hu.jcp.fridge.feature_food.domain.util.FoodOrder
import hu.jcp.fridge.feature_food.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFoods(
    private val repository: FoodRepository
) {

    operator fun invoke(
        foodOrder: FoodOrder = FoodOrder.Title(OrderType.Ascending)
    ): Flow<List<Food>> {
        return repository.getFoods().map { foods ->
            when(foodOrder.orderType) {
                is OrderType.Ascending -> {
                    when(foodOrder) {
                        is FoodOrder.Title -> foods.sortedBy { it.name.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when(foodOrder) {
                        is FoodOrder.Title -> foods.sortedByDescending { it.name.lowercase() }
                    }
                }
            }
        }
    }
}