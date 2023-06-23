package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(params: Params): Flow<Movie> =
        movieRepository.getMovieById(params.movieId)

    data class Params(
        val movieId: Int
    )

}