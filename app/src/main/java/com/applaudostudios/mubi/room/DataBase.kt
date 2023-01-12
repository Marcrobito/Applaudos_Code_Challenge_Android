package com.applaudostudios.mubi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.applaudostudios.mubi.room.dao.CardDao
import com.applaudostudios.mubi.room.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
abstract class DataBase: RoomDatabase()  {
    abstract fun cardDao():CardDao
}