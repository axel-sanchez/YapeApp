package com.example.yapeapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.data.source.RecipeLocalSource
import com.example.yapeapp.data.source.RecipeRemoteSource
import com.example.yapeapp.domain.repository.RecipeRepository
import com.example.yapeapp.helpers.Constants
import com.example.yapeapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemoteSource: RecipeRemoteSource,
    private val recipeLocalSource: RecipeLocalSource
) : RecipeRepository {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getAllRecipes(): Either<Constants.ApiError, List<Recipe?>> {

        val localRecipes = getLocalRecipes()
        if (localRecipes.isNotEmpty()) {
            return Either.Right(localRecipes)
        }

        val eitherRemoteRecipes = getRemoteRecipes()

        if (eitherRemoteRecipes is Either.Right) {
            addRecipesInDB(eitherRemoteRecipes.r)
            val sortedList = eitherRemoteRecipes.r.sortedWith(
                compareBy(
                    { it?.name?.substringBeforeLast(" ") }, { it?.description?.substringAfterLast(" ")?.toInt() }
                )
            )
            return Either.Right(sortedList)
        }

        return eitherRemoteRecipes
    }

    override suspend fun getLocalRecipes(): List<Recipe?> {
        return recipeLocalSource.getAllRecipes()
    }

    override suspend fun getRemoteRecipes(): Either<Constants.ApiError, List<Recipe?>> {
        return recipeRemoteSource.getRecipes().value ?: Either.Left(Constants.ApiError.API_ERROR)
    }

    override suspend fun getRecipe(idRecipe: Long): Recipe? {
        return recipeLocalSource.getRecipe(idRecipe)
    }

    override suspend fun addRecipesInDB(result: List<Recipe?>) {
        result.forEach { recipe ->
            recipe?.id = recipeLocalSource.insertRecipes(recipe)
        }
    }
}