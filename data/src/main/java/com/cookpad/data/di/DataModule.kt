package com.cookpad.data.di

import com.cookpad.common.Constants.BASE_URL
import com.cookpad.data.local.dao.CountryDao
import com.cookpad.data.local.dao.IngredientDao
import com.cookpad.data.local.dao.MealCategoryDao
import com.cookpad.data.local.dao.MealDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.data.repository.CountryRepositoryImpl
import com.cookpad.data.repository.IngredientsRepositoryImpl
import com.cookpad.data.repository.MealCategoryRepositoryImpl
import com.cookpad.data.repository.MealRepositoryImpl
import com.cookpad.domain.repository.CountryRepository
import com.cookpad.domain.repository.IngredientsRepository
import com.cookpad.domain.repository.MealCategoryRepository
import com.cookpad.domain.repository.MealRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CookPadApiService {
        return retrofit.create(CookPadApiService::class.java)
    }

    @Provides
    fun provideIngredientsRepository(
        apiService: CookPadApiService,
        ingredientDao: IngredientDao
    ): IngredientsRepository {
        return IngredientsRepositoryImpl(apiService, ingredientDao)
    }

    @Singleton
    @Provides
    fun provideMealCategoriesRepository(
        api: CookPadApiService,
        dao: MealCategoryDao
    ): MealCategoryRepository {
        return MealCategoryRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideCountriesRepository(api: CookPadApiService, dao: CountryDao): CountryRepository {
        return CountryRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideMealsRepository(api: CookPadApiService, dao: MealDao): MealRepository {
        return MealRepositoryImpl(api, dao)
    }

}