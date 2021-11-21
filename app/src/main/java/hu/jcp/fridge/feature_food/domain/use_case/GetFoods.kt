package hu.jcp.fridge.feature_food.domain.use_case

import hu.jcp.fridge.feature_food.data.data_source.model.Food
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFoods(
    private val repository: FoodRepository
) {

    operator fun invoke(): Flow<List<Food>> {
        return repository.getFoods().map { foods ->
            foods.sortedBy { it.name.lowercase() }
        }
    }
}