package com.fudie.data.remote.utils

fun String.getFlagUrl(): String {
    return countryFlags.find { countryFlags ->
        this == countryFlags.name
    }?.flagUrl ?: "https://cdn.pixabay.com/photo/2012/04/23/16/22/flag-38806_1280.png"
}


fun String?.getValueOrNull(): String {
    return if (this?.isEmpty() == true) "No value" else this ?: "No value"
}