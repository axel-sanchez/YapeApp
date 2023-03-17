package com.example.yapeapp.domain.usecase

import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.domain.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetRecipeUseCase{
    suspend fun call(idRecipe: Long): Recipe?
}

@Singleton
class GetRecipeUseCaseImpl @Inject constructor(private val repository: RecipeRepository): GetRecipeUseCase {
    override suspend fun call(idRecipe: Long): Recipe? {
        return repository.getRecipe(idRecipe)
    }
}