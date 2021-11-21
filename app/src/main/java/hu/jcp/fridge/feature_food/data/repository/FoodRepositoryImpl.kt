package hu.jcp.fridge.feature_food.data.repository

import hu.jcp.fridge.feature_food.data.data_source.FoodDao
import hu.jcp.fridge.feature_food.data.data_source.model.Food
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl(
    private val dao: FoodDao
) : FoodRepository{
    override fun getFoods(): Flow<List<Food>> {
        return dao.getFoods()
    }

    override suspend fun getFoodById(id: Int): Food? {
        return dao.getFoodById(id)
    }

    override suspend fun insertFood(food: Food) {
        return dao.insertFood(food)
    }

    override suspend fun deleteFood(food: Food) {
        return dao.deleteFood(food)
    }
}