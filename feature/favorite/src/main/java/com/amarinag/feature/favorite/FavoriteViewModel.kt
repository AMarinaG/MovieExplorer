package com.amarinag.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.core.common.result.Result
import com.amarinag.core.common.result.asResult
import com.amarinag.core.domain.DeleteFavoriteMoviesUseCase
import com.amarinag.core.domain.GetFavoriteMoviesUseCase
import com.amarinag.core.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase
) : ViewModel() {
    val favoriteUiState: StateFlow<FavoriteUiState> = getFavoriteMoviesUseCase()
        .asResult()
        .map { resultMovies ->
            when (resultMovies) {
                Result.Loading -> FavoriteUiState.Loading
                is Result.Success -> FavoriteUiState.Success(resultMovies.data)
                is Result.Error -> FavoriteUiState.Success(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoriteUiState.Loading
        )

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteFavoriteMoviesUseCase(DeleteFavoriteMoviesUseCase.Params(movie))
        }
    }
}