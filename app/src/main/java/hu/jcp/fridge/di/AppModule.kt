package hu.jcp.fridge.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.jcp.fridge.feature_food.data.data_source.FoodDatabase
import hu.jcp.fridge.feature_food.data.repository.FoodRepositoryImpl
import hu.jcp.fridge.feature_food.domain.repository.FoodRepository
import hu.jcp.fridge.feature_food.domain.use_case.DeleteFood
import hu.jcp.fridge.feature_food.domain.use_case.FoodUseCases
import hu.jcp.fridge.feature_food.domain.use_case.GetFoods
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodDatabase(app: Application) : FoodDatabase{
        return Room.databaseBuilder(
            app,
            FoodDatabase::class.java,
            FoodDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFoodRepository(db: FoodDatabase): FoodRepository{
        return FoodRepositoryImpl(db.foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodUseCase(repository: FoodRepository) : FoodUseCases{
        return FoodUseCases(
            getFoods = GetFoods(repository),
            deleteFood = DeleteFood(repository)
        )
    }
}