package com.cookpad.data.remote.dto

import com.cookpad.data.local.entity.IngredientEntity
import com.squareup.moshi.Json

data class IngredientDTO(
    @Json(name = "idIngredient")
    val idIngredient: String?,
    @Json(name = "strDescription")
    val strDescription: String?,
    @Json(name = "strIngredient")
    val strIngredient: String?,
    @Json(name = "strType")
    val strType: String? = "null"
) {
    fun toIngredientEntity(): IngredientEntity {
        return IngredientEntity(
            idIngredient = idIngredient ?: "",
            strDescription = strDescription ?: "",
            strIngredient = strIngredient ?: "",
            strType = strType ?: ""
        )

    }
}