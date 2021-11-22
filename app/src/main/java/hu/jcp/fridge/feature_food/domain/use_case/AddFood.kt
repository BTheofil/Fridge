package hu.jcp.fridge.feature_food.domain.use_case

import hu.jcp.fridge.feature_food.domain.model.Food
import hu.jcp.fridge.feature_food.domain.model.InvalidFoodException
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository
import kotlin.jvm.Throws

class AddFood(
    private val repository: FoodRepository
) {

    @Throws(InvalidFoodException::class)
    suspend operator fun invoke(food: Food){
        if(food.name.isBlank()){
            throw InvalidFoodException("The name can not be empty.")
        }
        repository.insertFood(food)
    }
}