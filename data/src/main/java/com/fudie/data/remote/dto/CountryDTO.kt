package com.fudie.data.remote.dto


import com.fudie.data.local.entity.CountryEntity
import com.fudie.data.remote.utils.getFlagUrl
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
