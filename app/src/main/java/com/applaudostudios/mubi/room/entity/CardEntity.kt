package com.applaudostudios.mubi.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val urlImg: String,
    val rating: Double
)