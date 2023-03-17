package com.example.yapeapp.domain.repository

import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.helpers.Constants
import com.example.yapeapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface RecipeRepository {
    suspend fun getAllRecipes(): Either<Constants.ApiError, List<Recipe?>>
    suspend fun getLocalRecipes(): List<Recipe?>
    suspend fun getRemoteRecipes(): Either<Constants.ApiError, List<Recipe?>>
    suspend fun getRecipe(idRecipe: Long): Recipe?
    suspend fun addRecipesInDB(result: List<Recipe?>)
}