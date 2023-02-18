package com.fudie.data.repository

import com.fudie.common.util.Resource
import com.fudie.data.local.dao.MealDao
import com.fudie.data.remote.CookPadApiService
import com.fudie.domain.model.Meal
import com.fudie.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
            dao.upsertMeals(remoteMeals.meals.map {
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
                dao.upsertMeals(remoteMeals.meals.map {
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
            dao.upsertMeals(remoteMeals.meals.map {
                it.toMealEntity().copy(strCountry = countryName)
            })
            emit(Resource.Success(data =dao.getMealsByCountryName(countryName).map { it.toDomain() }))
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

    override fun getaAllMeals(): Flow<List<Meal>> =
        dao.getAllMeals().map { response -> response.map { it.toDomain() } }

    override fun searchMeal(searchString: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        try {
            val meals = dao.getMealsByName(searchString).map {
                it.toDomain()
            }
            emit(Resource.Success(data = meals))
        } catch (e: Exception) {
            emit(
                Resource.Error(message = "Something went wrong")
            )
        }
    }

    override suspend fun toggleFavourite(isFavourite: Boolean, strMealId: String) =
        dao.updateFavourite(isFavourite, strMealId)

    override fun getFavouriteMeals(): Flow<List<Meal>> {
        return  dao.getFavouriteMeals().map { response -> response.map { it.toDomain() } }
    }

}