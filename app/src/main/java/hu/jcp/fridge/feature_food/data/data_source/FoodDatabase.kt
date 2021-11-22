package hu.jcp.fridge.feature_food.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.jcp.fridge.feature_food.domain.model.Food

@Database(
    entities = [Food::class],
    version = 1
)
abstract class FoodDatabase : RoomDatabase(){

    abstract val foodDao: FoodDao

    companion object{
        const val DATABASE_NAME = "foods_db"
    }
}