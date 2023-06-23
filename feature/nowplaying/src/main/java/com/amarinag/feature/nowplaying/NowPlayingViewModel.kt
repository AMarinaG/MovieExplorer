package com.amarinag.feature.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.core.common.result.Result
import com.amarinag.core.common.result.asResult
import com.amarinag.core.domain.GetNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(getNowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {
    val nowPlayingUiState: StateFlow<NowPlayingUiState> =
        getNowPlayingUseCase()
            .asResult()
            .map { resultMovies ->
                when (resultMovies) {
                    Result.Loading -> NowPlayingUiState.Loading
                    is Result.Error -> NowPlayingUiState.Error(resultMovies.exception?.localizedMessage.orEmpty())
                    is Result.Success -> NowPlayingUiState.Success(resultMovies.data)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NowPlayingUiState.Loading
            )
}