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

<<<<<<< HEAD
        val newMeals = dao.getMealsByName("Chicken").map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getBeefMeals(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByName("Beef").map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getBeefMeals()
            val listOfMealsToDelete =
                remoteMeals.meals.map { it.toMealEntity() }
                    .map { it.strMeal }
            dao.deleteMeals(listOfMealsToDelete)
            dao.insertMeals(remoteMeals.meals.map { it.toMealEntity() })
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

        val newMeals = dao.getMealsByName("Beef").map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getPorkMeals(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByName("Pork").map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getPorkMeals()
            val listOfMealsToDelete =
                remoteMeals.meals.map { it.toMealEntity() }
                    .map { it.strMeal }
            dao.deleteMeals(listOfMealsToDelete)
            dao.insertMeals(remoteMeals.meals.map { it.toMealEntity() })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localMeals
=======
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
            emit(Resource.Success(data = remoteMeals.meals.map {
                it.toMealEntity().toDomain()
            }))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection", data = localMeals
>>>>>>> main
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localMeals))
        }

<<<<<<< HEAD
        val newMeals = dao.getMealsByName("Pork").map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getVegetarianMeals(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByCategoryName("Vegetarian").map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getVegetarianMeal()
            val listOfMealsToDelete =
                remoteMeals.meals.map { it.toMealEntity() }
                    .map { it.strMeal }
            dao.deleteMeals(listOfMealsToDelete)
            dao.insertMeals(remoteMeals.meals.map { it.toMealEntity().copy(strCategory = "Vegetarian") })
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

        val newMeals = dao.getMealsByCategoryName("Vegetarian").map { it.toDomain() }
        emit(Resource.Success(newMeals))
    }

    override fun getBreakFastMeals(): Flow<Resource<List<Meal>>>  = flow {
        emit(Resource.Loading())
        val localMeals = dao.getMealsByCategoryName("Milk").map { it.toDomain() }
        emit(Resource.Loading(data = localMeals))
        try {
            val remoteMeals = api.getBreakfastMeal()
            val listOfMealsToDelete =
                remoteMeals.meals.map { it.toMealEntity() }
                    .map { it.strMeal }
            dao.deleteMeals(listOfMealsToDelete)
            dao.insertMeals(remoteMeals.meals.map { it.toMealEntity().copy(strCategory = "Milk") })
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

        val newMeals = dao.getMealsByCategoryName("Milk").map { it.toDomain() }
=======
        val newMeals = dao.getMealsByCountryName(countryName).map { it.toDomain() }
>>>>>>> main
        emit(Resource.Success(newMeals))
    }
}