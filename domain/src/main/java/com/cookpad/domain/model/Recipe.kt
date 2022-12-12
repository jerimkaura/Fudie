package com.cookpad.domain.model

data class Recipe(
    val dateModified: String,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrinkAlternate: String,
    val strImageSource: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val strTags: String,
    val strYoutube: String
) {

    fun toIngredientItem(): List<IngredientItem> {
        val ingredientItems: MutableList<IngredientItem> = ArrayList()
        if (!strIngredient1.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient1,
                strMeasure1
            )
        )
        if (!strIngredient2.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient2,
                strMeasure2
            )
        )
        if (!strIngredient3.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient3,
                strMeasure3
            )
        )
        if (!strIngredient4.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient4,
                strMeasure4
            )
        )
        if (!strIngredient5.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient5,
                strMeasure5
            )
        )
        if (!strIngredient6.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient6,
                strMeasure6
            )
        )
        if (!strIngredient7.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient7,
                strMeasure7
            )
        )
        if (!strIngredient8.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient8,
                strMeasure8
            )
        )
        if (!strIngredient9.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient9,
                strMeasure9
            )
        )
        if (!strIngredient10.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient10,
                strMeasure10
            )
        )
        if (!strIngredient11.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient11,
                strMeasure11
            )
        )
        if (!strIngredient12.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient12,
                strMeasure12
            )
        )
        if (!strIngredient13.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient13,
                strMeasure13
            )
        )
        if (!strIngredient14.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient14,
                strMeasure14
            )
        )
        if (!strIngredient15.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient15,
                strMeasure15
            )
        )
        if (!strIngredient16.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient16,
                strMeasure16
            )
        )
        if (!strIngredient17.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient17,
                strMeasure17
            )
        )
        if (!strIngredient18.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient18,
                strMeasure18
            )
        )
        if (!strIngredient19.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient19,
                strMeasure19
            )
        )
        if (!strIngredient20.contains("No value")) ingredientItems.add(
            IngredientItem(
                strIngredient20,
                strMeasure20
            )
        )
        return ingredientItems
    }
}
