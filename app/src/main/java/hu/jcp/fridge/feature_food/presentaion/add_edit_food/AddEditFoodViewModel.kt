package hu.jcp.fridge.feature_food.presentaion.add_edit_food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.fridge.feature_food.domain.use_case.FoodUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AddEditFoodViewModel @Inject constructor(
    private val foodUseCases: FoodUseCases
) : ViewModel(){

    private val _foodTitle = mutableStateOf(FoodTextFieldState(
        hint = "Enter title"
    ))
    val foodTitle : State<FoodTextFieldState> = _foodTitle

    private val _foodContent = mutableStateOf(FoodTextFieldState(
        hint = "Enter some content"
    ))
    val foodContent : State<FoodTextFieldState> = _foodContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditFoodEvent){
        when(event) {
            is AddEditFoodEvent.EnteredTitle -> {
                _foodTitle.value = foodTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditFoodEvent.ChangeTitleFocus -> {
                _foodTitle.value = foodTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&  foodTitle.value.text.isBlank()
                )
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val messages: String): UiEvent()
        object SaveFood: UiEvent()
    }
}