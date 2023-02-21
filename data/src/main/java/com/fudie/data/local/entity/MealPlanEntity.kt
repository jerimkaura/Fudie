package com.fudie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fudie.domain.model.MealPlan

@Entity
data class MealPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val longId: Long? = null,
    val strDayOfWeek: String,
    val boolIsFavourite: Boolean? = false,
    val strMealId: String,
    val strMeaL: String,
    val strMealThumbnail: String
) {
    fun toDomain(): MealPlan {
        return MealPlan(
            longId = longId ?: 0,
            strDayOfWeek = strDayOfWeek,
            boolIsFavourite = boolIsFavourite ?: false,
            strMealId = strMealId,
            strMeal = strMeaL,
            strMealThumbnail = strMealThumbnail
        )
    }
}
