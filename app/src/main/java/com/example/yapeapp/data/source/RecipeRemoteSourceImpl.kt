package com.example.yapeapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.data.service.ApiServiceRecipe
import com.example.yapeapp.helpers.Constants.ApiError
import com.example.yapeapp.helpers.Constants.ApiError.*
import com.example.yapeapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface RecipeRemoteSource {
    suspend fun getRecipes(): MutableLiveData<Either<ApiError, List<Recipe?>>>
}

@Singleton
class RecipeRemoteSourceImpl @Inject constructor(private val service: ApiServiceRecipe) : RecipeRemoteSource {
    override suspend fun getRecipes(): MutableLiveData<Either<ApiError, List<Recipe?>>> {
        val mutableLiveData = MutableLiveData<Either<ApiError, List<Recipe?>>>()

        try {
            val response = service.getRecipes()
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { recipes ->
                    mutableLiveData.value = Either.Right(recipes)
                } ?: kotlin.run {
                    mutableLiveData.value = Either.Left(API_ERROR)
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                val apiError = API_ERROR
                apiError.error = response.message()
                mutableLiveData.value = Either.Left(apiError)
            }
        } catch (e: Exception) {
            mutableLiveData.value = Either.Left(API_ERROR)
            Log.e(
                "ProductRemoteSourceImpl",
                e.message?:"Error al obtener los productos"
            )
            e.printStackTrace()
        }

        return mutableLiveData
    }
}