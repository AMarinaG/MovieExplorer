package com.amarinag.core.domain

import android.util.Log
import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.model.UserMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<UserMovie>> =
        combine(
            movieRepository.getPlayNowMovies(),
            movieRepository.getFavoriteMovies()
        ) { movies, favorites ->
            val userMovies = movies.map { movie ->
                UserMovie(movie, movie.id in favorites.map { it.id })
            }
            userMovies

        }

}