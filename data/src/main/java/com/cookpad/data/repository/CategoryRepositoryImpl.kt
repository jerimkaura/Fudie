package com.cookpad.data.repository

import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.MealCategoryDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.domain.model.MealCategory
import com.cookpad.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: MealCategoryDao
) : CategoryRepository {
    override fun getMealCategories(): Flow<Resource<List<MealCategory>>> = flow {
        emit(Resource.Loading())
        val localCategories = dao.getMealCategories().map { it.toDomain() }
        emit(Resource.Loading(data = localCategories))
        try {
            val remoteCategories = api.getMealCategories()
            val mealsToInsert = remoteCategories.categories ?: listOf()
            dao.upsertMeals(mealsToInsert.map { it.toEntity() })
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
}