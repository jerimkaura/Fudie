package com.cookpad.data.local.converters

import androidx.room.TypeConverter
import com.cookpad.domain.model.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters(private val gson: Gson) {

    fun mealToSJson(meal: Meal): String{
        return meal.toString()
    }

    @TypeConverter
    fun toMealJson(meal: Meal): String {
        return gson.toJson(
            meal,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}