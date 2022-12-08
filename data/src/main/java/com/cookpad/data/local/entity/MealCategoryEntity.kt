package com.cookpad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.domain.model.MealCategory

@Entity
data class MealCategoryEntity(
    @PrimaryKey
    val id: Int? = null,
    val strCategory: String,
    val idCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
) {
    fun toDomain(): MealCategory {
        return MealCategory(
            strCategory = strCategory,
            idCategory = idCategory,
            strCategoryDescription = strCategoryDescription,
            strCategoryThumb = strCategoryThumb
        )
    }
}
