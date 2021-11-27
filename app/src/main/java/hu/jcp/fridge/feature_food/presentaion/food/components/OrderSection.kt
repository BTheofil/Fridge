package hu.jcp.fridge.feature_food.presentaion.food.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.jcp.fridge.feature_food.domain.util.FoodOrder
import hu.jcp.fridge.feature_food.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    foodOrder: FoodOrder = FoodOrder.Title(OrderType.Descending),
    onOrderChange : (FoodOrder) -> Unit
){
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = foodOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(foodOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = foodOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(foodOrder.copy(OrderType.Descending)) }
            )
        }
    }
}