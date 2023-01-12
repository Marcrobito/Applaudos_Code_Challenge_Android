package com.applaudostudios.mubi.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.applaudostudios.mubi.room.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM cardEntity")
    fun getAll(): List<CardEntity>

    @Query("SELECT * FROM cardEntity WHERE id == :queryId")
    fun findById(queryId: Int): CardEntity?

    @Query("SELECT * FROM cardEntity WHERE name LIKE :query")
    fun findByName(query: String): List<CardEntity>

    @Insert
    fun insertAll(vararg cardEntity: CardEntity)

    @Delete
    fun delete(cardEntity: CardEntity)
}