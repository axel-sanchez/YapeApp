package com.example.yapeapp.domain.usecase

import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.domain.repository.RecipeRepository
import com.example.yapeapp.helpers.Constants
import com.example.yapeapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetAllRecipesUseCase{
    suspend fun call(): Either<Constants.ApiError, List<Recipe?>>
}

@Singleton
class GetAllRecipesUseCaseImpl @Inject constructor(private val repository: RecipeRepository): GetAllRecipesUseCase {
    override suspend fun call(): Either<Constants.ApiError, List<Recipe?>> {
        return repository.getAllRecipes()
    }
}