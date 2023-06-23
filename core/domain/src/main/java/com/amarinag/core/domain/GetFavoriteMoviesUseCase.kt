package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getFavoriteMovies()
}