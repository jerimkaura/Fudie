package com.fudie.data.local.dao

import androidx.room.*
import com.fudie.data.local.entity.CountryEntity

@Dao
interface CountryDao {
    @Upsert
    suspend fun upsertCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM countryEntity")
    suspend fun getCountries(): List<CountryEntity>
}