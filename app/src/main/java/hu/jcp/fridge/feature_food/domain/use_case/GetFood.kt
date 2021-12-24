package hu.jcp.fridge.feature_food.domain.use_case

import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository

class GetFood(
    private val repository: FoodRepository
) {

    suspend operator fun invoke(id: Int): Food? {
        return repository.getFoodById(id)
    }
}