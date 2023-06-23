package com.amarinag.feature.movie

import com.amarinag.core.model.UserMovie

sealed interface MovieUiState {
    object Loading : MovieUiState
    data class Error(val exception: Throwable? = null) : MovieUiState
    data class Success(val movie: UserMovie) : MovieUiState
}