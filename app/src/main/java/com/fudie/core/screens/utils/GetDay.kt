package com.fudie.core.screens.utils

import android.icu.util.Calendar

fun getDay(): String {
    val calendar = Calendar.getInstance()
    return when (calendar[Calendar.DAY_OF_WEEK]) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Time has stopped"
    }
}