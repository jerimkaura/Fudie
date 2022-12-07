package com.cookpad.data.di

import android.content.Context
import androidx.room.Room
import com.cookpad.common.Constants.BASE_URL
import com.cookpad.data.local.CookBookDatabase
import com.cookpad.data.local.dao.IngredientDao
import com.cookpad.data.remote.CookPadApiService
import com.cookpad.data.repository.IngredientsRepositoryImpl
import com.cookpad.domain.repository.IngredientsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CookBookDatabase {
        return Room.databaseBuilder(
            context,
            CookBookDatabase::class.java,
            "cookpad_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIngredientsDao(database: CookBookDatabase) = database.ingredientDao

    @Provides
    fun provideIngredientsRepository(
        apiService: CookPadApiService,
        ingredientDao: IngredientDao
    ): IngredientsRepository {
        return IngredientsRepositoryImpl(apiService, ingredientDao)
    }

}