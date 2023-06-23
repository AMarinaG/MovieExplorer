package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.model.Movie
import javax.inject.Inject

class DeleteFavoriteMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(params: Params) = movieRepository.deleteFavorite(params.movie)

    data class Params(
        val movie: Movie
    )
}