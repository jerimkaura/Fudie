package com.cookpad.data.di

import android.content.Context
import androidx.room.Room
import com.cookpad.data.local.CookBookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CookBookDatabase {
        return Room.databaseBuilder(
            context,
            CookBookDatabase::class.java,
            "cookpad_database",
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideIngredientDao(database: CookBookDatabase) = database.ingredientDao

    @Singleton
    @Provides
    fun provideMealCategoryDao(database: CookBookDatabase) = database.mealCategoryDao

    @Singleton
    @Provides
    fun provideCountryDao(database: CookBookDatabase) = database.countryDao

    @Singleton
    @Provides
    fun provideMealDao(database: CookBookDatabase) = database.mealDao

    @Singleton
    @Provides
    fun provideRecipeDao(database: CookBookDatabase) = database.recipeDao

    @Singleton
    @Provides
    fun provideMealPlanDao(database: CookBookDatabase) = database.mealPlanDao
}