package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.IngredientEntity
import com.cookpad.domain.model.Ingredient

@Dao
interface IngredientDao {
    @Upsert
    suspend fun upsertIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT * FROM ingrediententity WHERE idIngredient LIKE '%' || :ingredient || '%'")
    suspend fun getIngredients(ingredient: String): List<Ingredient>

    @Query("SELECT * FROM ingrediententity")
    suspend fun getIngredients(): List<IngredientEntity>
}