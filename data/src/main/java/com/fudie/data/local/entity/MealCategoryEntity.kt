package com.fudie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fudie.domain.model.MealCategory

@Entity
data class MealCategoryEntity(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
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
