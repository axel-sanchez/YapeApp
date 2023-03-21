package com.example.yapeapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yapeapp.data.model.Recipe

/**
 * @author Axel Sanchez
 */
@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe ORDER BY NAME, DESCRIPTION")
    suspend fun getAllRecipes(): List<Recipe?>

    @Query("SELECT * FROM Recipe where id = :idRecipe")
    suspend fun getRecipe(idRecipe: Long): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe?): Long
}