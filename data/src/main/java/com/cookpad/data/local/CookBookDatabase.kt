package com.cookpad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cookpad.data.local.dao.IngredientDao
import com.cookpad.data.local.entity.IngredientEntity

@Database(entities = [IngredientEntity::class], version = 1, exportSchema = false)
abstract class CookBookDatabase: RoomDatabase() {
    abstract val ingredientDao: IngredientDao
}