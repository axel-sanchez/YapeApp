package com.example.yapeapp.data.room

import androidx.room.TypeConverter
import com.example.yapeapp.data.model.Recipe
import com.google.gson.Gson

/**
 * @author Axel Sanchez
 */
class Converters{
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromRecipe(recipe: Recipe?): String? {
        recipe?.let {
            return gson.toJson(it)
        } ?: return null
    }

    @TypeConverter
    fun toRecipe(resultItemString: String?): Recipe? {
        resultItemString?.let {
            return gson.fromJson(it, Recipe::class.java)
        } ?: return null
    }

    @TypeConverter
    fun fromListRecipes(list: List<Recipe?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromRecipe(it[i])
                else ";${fromRecipe(it[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toListRecipes(concat: String?): List<Recipe?>? {
        val newList = concat?.split(";")
        newList?.let {
            return it.map { str -> toRecipe(str) }
        } ?: return null
    }
}