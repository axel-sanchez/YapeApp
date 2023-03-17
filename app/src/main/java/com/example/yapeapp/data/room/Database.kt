package com.example.yapeapp.data.room

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import com.example.yapeapp.data.model.Recipe

/**
 * Base de datos utilizando room
 * @author Axel Sanchez
 */
@Database(
    entities = [Recipe::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}