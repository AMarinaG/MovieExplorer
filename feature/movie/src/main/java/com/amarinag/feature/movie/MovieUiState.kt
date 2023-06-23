package com.amarinag.feature.movie

import com.amarinag.core.model.Movie
import java.lang.Exception

sealed interface MovieUiState {
    object Loading : MovieUiState
    data class Error(val exception: Throwable? = null) : MovieUiState
    data class Success(val movie: Movie) : MovieUiState
}