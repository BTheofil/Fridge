package hu.jcp.fridge.feature_food.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food (
    @PrimaryKey val id: Int? = null,
    val name: String,
    val quantity : Float,
    val measure : MEASURE,
)

enum class MEASURE{
    PIECE, LITER, KG
}

class InvalidFoodException(message: String) : Exception(message)
