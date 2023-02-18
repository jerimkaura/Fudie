package com.fudie.core.screens.recipe.components

import com.fudie.core.R
import com.fudie.core.screens.utils.Constants.INGREDIENTS
import com.fudie.core.screens.utils.Constants.INSTRUCTIONS

sealed class MealTabs(var icon: Int, var title: String) {
    object Ingredients : MealTabs(R.drawable.ic_terms_service, INGREDIENTS)
    object Instructions : MealTabs(R.drawable.ic_notes, INSTRUCTIONS)
}