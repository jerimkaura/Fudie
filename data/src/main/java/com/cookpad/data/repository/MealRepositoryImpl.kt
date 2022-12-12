package com.cookpad.data.repository

import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.MealDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.domain.model.Meal
import com.cookpad.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: MealDao
) :
    MealRepository {
    override fun getMealByCategoryName(categoryName: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByName(categoryName).map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getMealsByCategoryName(categoryName)
            dao.insertMeals(remoteMeals.meals.map {
                it.toMealEntity().copy(strCategory = categoryName)
            })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localMeals
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localMeals))
        }

        val newMeals = dao.getMealsByCategoryName(categoryName).map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getMealByIngredientName(ingredientName: String): Flow<Resource<List<Meal>>> =
        flow {
            emit(Resource.Loading())
            val localMeals = dao.getMealsByName(ingredientName).map { it.toDomain() }
            emit(Resource.Loading(data = localMeals))
            try {
                val remoteMeals = api.getMealsByIngredientName(ingredientName)
                dao.insertMeals(remoteMeals.meals.map {
                    it.toMealEntity().copy(strCategory = ingredientName)
                })
                emit(Resource.Success(data = remoteMeals.meals.map {
                    it.toMealEntity().toDomain()
                }))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Please check your internet connection", data = localMeals
                    )
                )
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Something went wrong", data = localMeals))
            }

            val newMeals = dao.getMealsByCategoryName(ingredientName).map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getMealByCountryName(countryName: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByCountryName(countryName).map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getMealByCountryName(countryName)
            dao.insertMeals(remoteMeals.meals.map {
                it.toMealEntity().copy(strCountry = countryName)
            })
            emit(Resource.Success(data = remoteMeals.meals.map {
                it.toMealEntity().toDomain()
            }))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection", data = localMeals
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localMeals))
        }

        val newMeals = dao.getMealsByCountryName(countryName).map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }
}