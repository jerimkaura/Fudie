package com.cookpad.data.repository

import com.cookpad.common.util.Resource
import com.cookpad.data.local.dao.CountryDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.domain.model.Country
import com.cookpad.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: CountryDao
) : CountryRepository {
    override fun getCountries(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading())
        val localCountries = dao.getCountries().map { it.toDomain() }
        emit(Resource.Loading(data = localCountries))
        try {
            val remoteCountries = api.getMealCountries()
            val listOfCountriesToDelete =
                remoteCountries.meals.map { it.toCountryEntity() }
                    .map { it.strArea }
            dao.deleteCountries(listOfCountriesToDelete)
            dao.insertCountries(remoteCountries.meals.map { it.toCountryEntity() })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localCountries
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localCountries))
        }

        val newCountries = dao.getCountries().map { it.toDomain() }
        emit(Resource.Success(newCountries))

    }
}