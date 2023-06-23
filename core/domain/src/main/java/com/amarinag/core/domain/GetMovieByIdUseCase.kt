package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.model.UserMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(params: Params): Flow<UserMovie> =
        combine(
            movieRepository.getMovieById(params.movieId),
            movieRepository.getFavoriteMovies()
        ) { movie, favorites ->
            UserMovie(
                movie = movie,
                isFavorite = movie.id in favorites.map { it.id }
            )
        }

    data class Params(
        val movieId: Int
    )

}