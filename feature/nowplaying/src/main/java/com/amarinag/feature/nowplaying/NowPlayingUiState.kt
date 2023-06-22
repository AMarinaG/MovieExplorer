package com.amarinag.feature.nowplaying

import com.amarinag.core.model.Movie

interface NowPlayingUiState {
    object Loading : NowPlayingUiState
    data class Success(val movies: List<Movie>) : NowPlayingUiState
    data class Error(val message: String): NowPlayingUiState
}
