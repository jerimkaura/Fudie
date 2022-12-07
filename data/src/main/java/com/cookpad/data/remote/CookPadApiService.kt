package com.cookpad.data.remote

import com.cookpad.data.remote.dto.IngredientsResponse
import retrofit2.http.GET

interface CookPadApiService {
    companion object {
        const val GET_ALL_INGREDIENTS = "list.php?i=list"
    }

    @GET(GET_ALL_INGREDIENTS)
    suspend fun getIngredients(): IngredientsResponse
}