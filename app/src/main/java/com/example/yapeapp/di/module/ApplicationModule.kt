package com.example.yapeapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.yapeapp.data.room.Database
import com.example.yapeapp.data.repository.RecipeRepositoryImpl
import com.example.yapeapp.data.service.ApiClient
import com.example.yapeapp.data.service.ApiServiceRecipe
import com.example.yapeapp.data.source.RecipeLocalSource
import com.example.yapeapp.data.source.RecipeLocalSourceImpl
import com.example.yapeapp.data.source.RecipeRemoteSource
import com.example.yapeapp.data.source.RecipeRemoteSourceImpl
import com.example.yapeapp.domain.repository.RecipeRepository
import com.example.yapeapp.domain.usecase.GetAllRecipesUseCase
import com.example.yapeapp.domain.usecase.GetAllRecipesUseCaseImpl
import com.example.yapeapp.helpers.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Module
class ApplicationModule(private val context: Context){
    @Provides
    @Singleton
    fun provideRecipeRepository(recipeRepository: RecipeRepositoryImpl): RecipeRepository = recipeRepository

    @Provides
    @Singleton
    fun provideRecipeRemoteSource(recipeRemoteSource: RecipeRemoteSourceImpl): RecipeRemoteSource = recipeRemoteSource

    @Provides
    @Singleton
    fun provideGetAllRecipesUseCase(getAllRecipesUseCase: GetAllRecipesUseCaseImpl): GetAllRecipesUseCase = getAllRecipesUseCase

    @Provides
    @Singleton
    fun provideApiServiceRecipe(): ApiServiceRecipe {
        return ApiClient.Builder<ApiServiceRecipe>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiServiceRecipe::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        return Room
            .databaseBuilder(context, Database::class.java, "YapeDB.db3")
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeLocalSource(database: Database): RecipeLocalSource {
        return RecipeLocalSourceImpl(database.recipeDao())
    }

    @Provides
    @Singleton
    fun provideContext(): Context = context
}