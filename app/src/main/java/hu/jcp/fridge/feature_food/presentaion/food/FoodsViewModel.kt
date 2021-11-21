package hu.jcp.fridge.feature_food.presentaion.food

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.fridge.feature_food.domain.use_case.FoodUseCases
import javax.inject.Inject

@HiltViewModel
class FoodsViewModel @Inject constructor(
    private val foodUseCases: FoodUseCases
) : ViewModel() {


}