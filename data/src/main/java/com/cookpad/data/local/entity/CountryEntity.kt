package com.cookpad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.domain.model.Country

@Entity
data class CountryEntity(
    @PrimaryKey
    val strArea: String,
    val flagUrl: String
) {
    fun toDomain(): Country {
        return Country(
            strArea = strArea,
            flagUrl = flagUrl
        )
    }
}
