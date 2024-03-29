package com.fudie.data.di

import com.fudie.common.Constants.BASE_URL
import com.fudie.data.local.dao.*
import com.fudie.data.remote.CookPadApiService
import com.fudie.data.repository.*
import com.fudie.domain.repository.*
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
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CookPadApiService {
        return retrofit.create(CookPadApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideIngredientsRepository(
        apiService: CookPadApiService,
        ingredientDao: IngredientDao
    ): IngredientRepository {
        return IngredientRepositoryImpl(apiService, ingredientDao)
    }

    @Singleton
    @Provides
    fun provideMealCategoriesRepository(
        api: CookPadApiService,
        dao: MealCategoryDao,
        mealDao: MealDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(api, dao, mealDao = mealDao)
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

    @Singleton
    @Provides
    fun provideRecipeRepository(
        api: CookPadApiService,
        recipeDao: RecipeDao,
        mealDao: MealDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(api = api, recipeDao = recipeDao, mealDao = mealDao)
    }

    @Singleton
    @Provides
    fun providePlannerRepository(planDao: MealPlanDao): MealPlanRepository {
        return MealPlanRepositoryImpl(dao = planDao)
    }
}