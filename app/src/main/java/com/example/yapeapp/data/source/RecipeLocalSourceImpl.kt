package com.example.yapeapp.data.source

import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.data.room.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface RecipeLocalSource {
    suspend fun getAllRecipes(): List<Recipe?>
    suspend fun getRecipe(idRecipe: Long): Recipe?
    suspend fun insertRecipes(recipe: Recipe?): Long
}

@Singleton
class RecipeLocalSourceImpl @Inject constructor(private val database: RecipeDao):
    RecipeLocalSource {
    override suspend fun getAllRecipes(): List<Recipe?> {
        return database.getAllRecipes()
    }

    override suspend fun insertRecipes(recipe: Recipe?): Long {
        return database.insertRecipe(recipe)
    }

    override suspend fun getRecipe(idRecipe: Long): Recipe? {
        return database.getRecipe(idRecipe)
    }
}