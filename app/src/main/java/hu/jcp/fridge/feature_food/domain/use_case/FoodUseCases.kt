package hu.jcp.fridge.feature_food.domain.use_case

data class FoodUseCases(
    val getFoods: GetFoods,
    val deleteFood: DeleteFood,
    val addFood: AddFood
)