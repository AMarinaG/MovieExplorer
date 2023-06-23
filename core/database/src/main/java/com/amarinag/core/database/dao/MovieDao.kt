package com.amarinag.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.amarinag.core.database.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
}