package com.amarinag.feature.nowplaying

import com.amarinag.core.model.Movie
import com.amarinag.core.model.UserMovie

interface NowPlayingUiState {
    object Loading : NowPlayingUiState
    data class Success(val movies: List<UserMovie>) : NowPlayingUiState
    data class Error(val message: String): NowPlayingUiState
}
