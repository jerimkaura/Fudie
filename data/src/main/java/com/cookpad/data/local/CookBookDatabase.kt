package com.cookpad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cookpad.data.local.dao.CountryDao
import com.cookpad.data.local.dao.IngredientDao
import com.cookpad.data.local.dao.MealCategoryDao
import com.cookpad.data.local.dao.MealDao
import com.cookpad.data.local.entity.CountryEntity
import com.cookpad.data.local.entity.IngredientEntity
import com.cookpad.data.local.entity.MealCategoryEntity
import com.cookpad.data.local.entity.MealEntity

@Database(
    entities = [IngredientEntity::class, MealCategoryEntity::class, CountryEntity::class, MealEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CookBookDatabase : RoomDatabase() {
    abstract val ingredientDao: IngredientDao
    abstract val mealCategoryDao: MealCategoryDao
    abstract val countryDao: CountryDao
    abstract val mealDao: MealDao
}