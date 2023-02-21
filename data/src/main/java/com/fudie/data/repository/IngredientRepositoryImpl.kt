package com.fudie.data.repository

import com.fudie.common.util.Resource
import com.fudie.data.local.dao.IngredientDao
import com.fudie.data.remote.CookPadApiService
import com.fudie.domain.model.Ingredient
import com.fudie.domain.repository.IngredientRepository
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