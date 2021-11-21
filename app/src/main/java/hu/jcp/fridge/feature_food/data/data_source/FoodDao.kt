package hu.jcp.fridge.feature_food.data.data_source

import androidx.room.*
import hu.jcp.fridge.feature_food.data.data_source.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getFoods(): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getFoodById(id: Int) : Food?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)
}