package com.example.yapeapp.data.service

import com.example.yapeapp.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Axel Sanchez
 */
interface ApiServiceRecipe{
    @GET("list")
    suspend fun getRecipes(): Response<List<Recipe?>?>
}