package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cookpad.data.local.entity.IngredientEntity

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Query("DELETE FROM ingredientEntity WHERE idIngredient in(:ingredients)")
    suspend fun deleteIngredients(ingredients: List<String>)

//    @Query("SELECT * FROM ingrediententity WHERE idIngredient LIKE '%' || :ingredient || '%'")
//    suspend fun getIngredients(ingredient: String): List<Ingredient>

    @Query("SELECT * FROM ingrediententity")
    suspend fun getIngredients(): List<IngredientEntity>
}