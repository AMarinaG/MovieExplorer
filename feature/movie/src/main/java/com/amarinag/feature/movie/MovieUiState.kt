package com.amarinag.feature.movie

import com.amarinag.core.model.Movie

sealed interface MovieUiState {
    object Loading : MovieUiState
    data class Success(val movie: Movie) : MovieUiState
}