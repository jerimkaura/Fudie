package com.cookpad.data.repository

import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.IngredientDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.domain.model.Ingredient
import com.cookpad.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val ingredientDao: IngredientDao
) : IngredientRepository {
    override fun getIngredients(): Flow<Resource<List<Ingredient>>> = flow {
        emit(Resource.Loading())
        val localIngredients = ingredientDao.getIngredients().map { it.toDomain() }
        emit(Resource.Loading(data = localIngredients))
        try {
            val remoteIngredients = api.getIngredients().meals ?: emptyList()
            ingredientDao.upsertIngredients(remoteIngredients.map { it.toIngredientEntity() })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localIngredients
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localIngredients))
        }

        val newIngredients = ingredientDao.getIngredients().map { it.toDomain() }
        emit(Resource.Success(newIngredients))
    }

}