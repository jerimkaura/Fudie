package com.cookpad.data.local.dao

import androidx.room.*
import com.cookpad.data.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Upsert
    suspend fun upsertMeals(meals: List<MealEntity>)

    @Query("SELECT * FROM mealEntity")
    fun getAllMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM mealEntity WHERE boolIsFavourite = 1")
    fun getFavouriteMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM mealEntity WHERE strMeal  LIKE '%' || :mealName || '%'")
    suspend fun getMealsByName(mealName: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCategory  LIKE '%' || :mealCategory || '%'")
    suspend fun getMealsByCategoryName(mealCategory: String): List<MealEntity>

    @Query("SELECT * FROM mealEntity WHERE strCountry LIKE '%' || :countryName || '%' ")
    suspend fun getMealsByCountryName(countryName: String): List<MealEntity>

    @Query("UPDATE mealEntity SET boolIsFavourite = :isFavourite WHERE idMeal =:idMeal")
    suspend fun updateFavourite(isFavourite: Boolean?, idMeal: String?)
}