package com.amarinag.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.core.common.result.Result
import com.amarinag.core.common.result.asResult
import com.amarinag.core.domain.AddFavoriteMoviesUseCase
import com.amarinag.core.domain.DeleteFavoriteMoviesUseCase
import com.amarinag.core.domain.GetMovieByIdUseCase
import com.amarinag.core.model.Movie
import com.amarinag.core.model.UserMovie
import com.amarinag.feature.movie.navigation.MovieArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    getMovieByIdUseCase: GetMovieByIdUseCase,
    savedStateHandle: SavedStateHandle,
    private val addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase,
    private val deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase
) : ViewModel() {
    private val movieArgs: MovieArgs = MovieArgs(savedStateHandle)
    val movieUiState: StateFlow<MovieUiState> =
        getMovieByIdUseCase(params = GetMovieByIdUseCase.Params(movieArgs.movieId))
            .asResult()
            .map { movieResult ->
                when (movieResult) {
                    is Result.Error -> MovieUiState.Error(movieResult.exception)
                    Result.Loading -> MovieUiState.Loading
                    is Result.Success -> MovieUiState.Success(movieResult.data)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MovieUiState.Loading
            )

    fun toggleFavorite(userMovie: UserMovie) {
        viewModelScope.launch {
            if (userMovie.isFavorite) {
                deleteFavoriteMoviesUseCase(DeleteFavoriteMoviesUseCase.Params(userMovie.movie))
            } else {
                addFavoriteMoviesUseCase(AddFavoriteMoviesUseCase.Params(userMovie.movie))
            }
        }
    }
}