package com.cookpad.data.repository

import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.MealDao
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
    private val recipeDao: RecipeDao,
    private val mealDao: MealDao
) :
    RecipeRepository {
    override fun getRandomMeal(): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        val randomLocalRecipe: Recipe?
        val recipes = recipeDao.getRecipes() ?: emptyList()
        if (recipes.isNotEmpty()){
            randomLocalRecipe = recipes.random().toDomain()
            emit(Resource.Success(data = randomLocalRecipe))
        }
        try {
            val remoteRecipe = api.getRandomMeal().meals?.firstOrNull()
            remoteRecipe?.let {
                recipeDao.upsertRecipe(it.toEntity())
            }
            val recipe = remoteRecipe?.toEntity()?.idMeal?.let { recipeDao.getRecipeByMealId(it) }
            recipe?.let { recipeEntity ->
                emit(Resource.Success(recipeEntity.toDomain()))
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
        val newRecipes = recipeDao.getRecipes() ?: emptyList()
        newRecipes.isNotEmpty().let {
            if (it){
                emit(Resource.Success(data = newRecipes.random().toDomain()))
            }
        }

    }

    override fun getRecipeByMealId(mealId: String): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        recipeDao.getRecipeByMealId(mealId)?.toDomain()?.let {
            emit(Resource.Success(data = it))
        }
        try {
            api.getRecipeByMealId(mealId).meals?.firstOrNull()?.toEntity().let {
                it?.let {
                    recipeDao.upsertRecipe(it)
                }
            }
            val localRecipe =
                recipeDao.getRecipeByMealId(mealId)
                    ?: api.getRecipeByMealId(mealId).meals?.firstOrNull()
                        ?.toEntity()
            localRecipe?.let {
                recipeDao.upsertRecipe(it)
                emit(Resource.Success(data = recipeDao.getRecipeByMealId(it.idMeal)!!.toDomain()))
            }
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
        recipeDao.getRecipeByMealId(mealId)?.toDomain()?.let {
            emit(Resource.Success(it))
        }
    }

    override suspend fun getAllRecipes(): List<Recipe> {
        mealDao.getAllMeals().forEach { meal ->
            api.getRecipeByMealId(meal.idMeal).meals?.let { recipes ->
                recipeDao.insertRecipes(recipes.map { recipeDto ->
                    recipeDto.toEntity()
                })
            }
        }
        return recipeDao.getRecipes()?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override fun searchRecipe(searchString: String): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        recipeDao.searchRecipe(searchString).let { recipes ->
            if (recipes.isNotEmpty()) {
                emit(Resource.Success(data = recipes.map { it.toDomain() }))
            } else {
                try {
                    val onlineResults = api.searchMeal(searchString).meals ?: emptyList()
                    val convertedRecipes = onlineResults.map {
                      it.toEntity().toDomain()
                   }
                    emit(Resource.Success(data = convertedRecipes))
                    recipeDao.insertRecipes(onlineResults.map { it.toEntity() })
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
            }
        }

    }
}