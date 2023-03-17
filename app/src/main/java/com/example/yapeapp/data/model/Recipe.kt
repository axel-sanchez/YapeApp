package com.example.yapeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var name: String? = null,
    var description: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var image: String? = null
)