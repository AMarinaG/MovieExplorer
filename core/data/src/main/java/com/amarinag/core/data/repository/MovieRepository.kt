package com.amarinag.core.data.repository

import com.amarinag.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPlayNowMovies(): Flow<List<Movie>>

    fun getMovieById(movieId: Int): Flow<Movie>

    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun addFavorite(movie: Movie)
    suspend fun deleteFavorite(movie: Movie)
}