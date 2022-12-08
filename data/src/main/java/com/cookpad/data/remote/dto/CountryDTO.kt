package com.cookpad.data.remote.dto


import com.cookpad.data.local.entity.CountryEntity
import com.cookpad.data.remote.utils.getFlagUrl
import com.squareup.moshi.Json

data class CountryDTO(
    @Json(name = "strArea")
    val strArea: String
){
    fun toCountryEntity(): CountryEntity{
        return CountryEntity(
            strArea = strArea,
            flagUrl = strArea.getFlagUrl()
        )
    }
}
