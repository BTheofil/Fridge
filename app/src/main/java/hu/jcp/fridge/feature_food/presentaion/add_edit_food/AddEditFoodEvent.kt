package hu.jcp.fridge.feature_food.presentaion.add_edit_food

import androidx.compose.ui.focus.FocusState

sealed class AddEditFoodEvent{
    data class EnteredTitle(val value: String): AddEditFoodEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditFoodEvent()
    data class EnteredContent(val value: String): AddEditFoodEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditFoodEvent()
    //data class ChangeColor(val color: Int): AddEditFoodEvent()
    object SaveFood: AddEditFoodEvent()
}