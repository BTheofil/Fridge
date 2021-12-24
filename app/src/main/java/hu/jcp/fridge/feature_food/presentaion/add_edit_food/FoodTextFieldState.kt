package hu.jcp.fridge.feature_food.presentaion.add_edit_food

data class FoodTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
