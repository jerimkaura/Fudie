package com.cookpad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cookpad.data.local.entity.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM countryEntity")
    suspend fun getCountries(): List<CountryEntity>

    @Query("DELETE  FROM countryentity WHERE strArea IN (:areas)")
    suspend fun deleteCountries(areas: List<String>)
}