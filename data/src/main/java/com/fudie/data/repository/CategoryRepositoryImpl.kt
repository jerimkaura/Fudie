package com.fudie.data.repository

import com.fudie.common.util.Resource
import com.fudie.data.local.dao.MealCategoryDao
import com.fudie.data.local.dao.MealDao
import com.fudie.data.remote.CookPadApiService
import com.fudie.domain.model.MealCategory
import com.fudie.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: MealCategoryDao,
    private val mealDao: MealDao
) : CategoryRepository {
    override fun getMealCategories(): Flow<Resource<List<MealCategory>>> = flow {
        emit(Resource.Loading())
        val localCategories = dao.getMealCategories().map { it.toDomain() }
        emit(Resource.Loading(data = localCategories))
        try {
            val remoteCategories = api.getMealCategories()
            val categoriesToInsert = remoteCategories.categories ?: listOf()
            dao.upsertMeals(categoriesToInsert.map { it.toEntity() })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localCategories
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localCategories))
        }

        val newMealCategories = dao.getMealCategories().map { it.toDomain() }
        emit(Resource.Success(newMealCategories))
    }

    override suspend fun getMealsByCategories() {
        dao.getMealCategories().forEach { mealCategoryEntity ->
            api.getMealsByCategoryName(mealCategoryEntity.strCategory).meals.let { meals ->
                mealDao.upsertMeals(meals.map { it.toMealEntity() })
            }
        }
    }
}