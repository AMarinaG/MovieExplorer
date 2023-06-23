package com.amarinag.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amarinag.core.database.dao.MovieDao
import com.amarinag.core.database.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = true)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}