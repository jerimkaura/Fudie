package com.cookpad.data.repository

import android.util.Log
import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.RecipeDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.domain.model.Recipe
import com.cookpad.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: RecipeDao
) :
    RecipeRepository {
    override fun getRandomMeal(): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            val remoteRecipe = api.getRandomMeal().meals.first()
            dao.upsertRecipe(remoteRecipe.toEntity())
            val recipe = dao.getRecipeByMealId(remoteRecipe.toEntity().idMeal)
            if (recipe != null) {
                emit(Resource.Success(recipe.toDomain()))
            }
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong"))
        }
    }

    override fun getRecipeByMealId(mealId: String): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            val localRecipe = dao.getRecipeByMealId(mealId)?.toDomain() ?: api.getRecipeByMealId(
                mealId
            ).meals.first().toEntity().toDomain()
            val remoteRecipe = api.getRecipeByMealId(mealId).meals.first()
            dao.upsertRecipe(remoteRecipe.toEntity())
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                )
            )
        }
        val newLocalRecipe = dao.getRecipeByMealId(mealId)?.toDomain()
        if (newLocalRecipe != null) {
            emit(Resource.Success(newLocalRecipe))
        }
    }
}