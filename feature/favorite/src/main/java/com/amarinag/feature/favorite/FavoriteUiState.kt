package com.amarinag.feature.favorite

import com.amarinag.core.model.Movie

sealed interface FavoriteUiState {
    object Loading: FavoriteUiState
    data class Success(val movies: List<Movie>): FavoriteUiState
}