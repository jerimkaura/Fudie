package com.cookpad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.domain.model.Ingredient

@Entity
data class IngredientEntity(
    @PrimaryKey
    val id: Int? = null,
    val idIngredient: String,
    val strDescription: String,
    val strIngredient: String,
    val strType: String
) {
    fun toDomain(): Ingredient {
        return Ingredient(
            idIngredient = idIngredient,
            strDescription = strDescription,
            strIngredient = strIngredient,
            strType = strType
        )
    }
}

