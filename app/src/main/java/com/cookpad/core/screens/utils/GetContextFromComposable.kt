package com.cookpad.core.screens.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.cookpad.core.dataStore
import com.cookpad.domain.model.Recipe

fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

suspend fun saveRecipeLocally(context: Context, data: Recipe) {
    context.dataStore.updateData {
        it.copy(
            idMeal = data.idMeal,
            dateModified = data.dateModified,
            strArea = data.strArea,
            strCategory = data.strCategory,
            strCreativeCommonsConfirmed = data.strCreativeCommonsConfirmed,
            strDrinkAlternate = data.strDrinkAlternate,
            strImageSource = data.strImageSource,
            strIngredient1 = data.strIngredient1,
            strIngredient2 = data.strIngredient2,
            strIngredient3 = data.strIngredient3,
            strIngredient4 = data.strIngredient4,
            strIngredient5 = data.strIngredient5,
            strIngredient6 = data.strIngredient6,
            strIngredient7 = data.strIngredient7,
            strIngredient8 = data.strIngredient8,
            strIngredient9 = data.strIngredient9,
            strIngredient10 = data.strIngredient10,
            strIngredient11 = data.strIngredient11,
            strIngredient12 = data.strIngredient12,
            strIngredient13 = data.strIngredient13,
            strIngredient14 = data.strIngredient14,
            strIngredient15 = data.strIngredient15,
            strIngredient16 = data.strIngredient16,
            strIngredient17 = data.strIngredient17,
            strIngredient18 = data.strIngredient18,
            strIngredient19 = data.strIngredient19,
            strIngredient20 = data.strIngredient20,
            strInstructions = data.strInstructions,
            strMeal = data.strMeal,
            strMealThumb = data.strMealThumb,
            strMeasure1 = data.strMeasure1,
            strMeasure2 = data.strMeasure2,
            strMeasure3 = data.strMeasure3,
            strMeasure4 = data.strMeasure4,
            strMeasure5 = data.strMeasure5,
            strMeasure6 = data.strMeasure6,
            strMeasure7 = data.strMeasure7,
            strMeasure8 = data.strMeasure8,
            strMeasure9 = data.strMeasure9,
            strMeasure10 = data.strMeasure10,
            strMeasure11 = data.strMeasure11,
            strMeasure12 = data.strMeasure12,
            strMeasure13 = data.strMeasure13,
            strMeasure14 = data.strMeasure14,
            strMeasure15 = data.strMeasure15,
            strMeasure16 = data.strMeasure16,
            strMeasure17 = data.strMeasure17,
            strMeasure18 = data.strMeasure18,
            strMeasure19 = data.strMeasure19,
            strMeasure20 = data.strMeasure20,
            strSource = data.strSource,
            strTags = data.strTags,
            strYoutube = data.strYoutube
        )
    }
}