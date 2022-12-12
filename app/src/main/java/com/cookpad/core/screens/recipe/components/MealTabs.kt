package com.cookpad.core.screens.recipe.components

import com.cookpad.core.R
import com.cookpad.core.screens.utils.Constants.INFO
import com.cookpad.core.screens.utils.Constants.INGREDIENTS
import com.cookpad.core.screens.utils.Constants.INSTRUCTIONS

sealed class MealTabs(var icon: Int, var title: String) {
    object Ingredients : MealTabs(R.drawable.ic_terms_service, INGREDIENTS)
    object Instructions : MealTabs(R.drawable.ic_notes, INSTRUCTIONS)
    object Details : MealTabs(R.drawable.ic_policy, INFO)
}