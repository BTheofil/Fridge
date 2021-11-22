package hu.jcp.fridge.feature_food.domain.repository

import hu.jcp.fridge.feature_food.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    fun getFoods(): Flow<List<Food>>

    suspend fun getFoodById(id: Int): Food?

    suspend fun insertFood(food: Food)

    suspend fun deleteFood(food: Food)

}