package com.cookpad.data.repository

import android.util.Log
import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.MealPlanDao
import com.cookpad.data.local.entity.MealPlanEntity
import com.cookpad.domain.model.MealPlan
import com.cookpad.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(private val dao: MealPlanDao) :
    MealPlanRepository {
    override fun createMealPlan(mealPlans: List<MealPlan>): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val mealPlanEntities = mealPlans.map { mealPlan ->
                MealPlanEntity(
                    strDayOfWeek = mealPlan.strDayOfWeek,
                    strMealId = mealPlan.strMealId,
                    strMealThumbnail = mealPlan.strMealThumbnail, strMeaL = mealPlan.strMeal
                )
            }
            dao.insertMealPlans(mealPlanEntities)
            emit(Resource.Success(data = "Plan created successfully"))
        } catch (ex: Exception) {
            val error = ex.localizedMessage ?: "Unknown error occurred"
            emit(Resource.Error(error))
        }

    }

    override fun getMealPlans(): Flow<Resource<List<MealPlan>>> = flow {
        emit(Resource.Loading())
        try {
            val mealPlans = dao.getMealPlans().map { it.toDomain() }
            emit(Resource.Success(data = mealPlans))
        } catch (ex: Exception) {
            val error = ex.localizedMessage ?: "Unknown error occurred"
            emit(Resource.Error(error))
        }
    }

    override fun getMealPlansByDayOfTheWeek(strDayOfWeek: String): Flow<Resource<List<MealPlan>>> =
        flow {
            try {
                val mealPlans = dao.getMealPlanByDayOfTheWeek(strDayOfWeek).map { it.toDomain() }
                emit(Resource.Success(data = mealPlans))
            } catch (ex: Exception) {
                val error = ex.localizedMessage ?: "Unknown error occurred"
                emit(Resource.Error(error))
            }
        }
}