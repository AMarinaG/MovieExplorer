package com.amarinag.feature.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.amarinag.core.designsystem.component.LoadingState
import com.amarinag.core.designsystem.theme.spacing
import com.amarinag.core.model.Movie

@Composable
internal fun MovieRoute(onBackClick: () -> Unit, viewModel: MovieViewModel = hiltViewModel()) {
    val movieState by viewModel.movieUiState.collectAsStateWithLifecycle()
    MovieScreen(movieState, onBackClick, viewModel::toggleFavorite)
}

@Composable
internal fun MovieScreen(
    movieState: MovieUiState,
    onBackClick: () -> Unit,
    toggleFavorite: (movie: Movie) -> Unit
) {
    when (movieState) {
        MovieUiState.Loading -> LoadingState()
        is MovieUiState.Success -> MovieDetail(
            movie = movieState.movie,
            onBackClick = onBackClick,
            toggleFavorite = toggleFavorite
        )
    }
}

@Composable
internal fun MovieDetail(
    movie: Movie,
    onBackClick: () -> Unit,
    toggleFavorite: (movie: Movie) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.backdropPath}",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.normal)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = { toggleFavorite(movie) }) {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                }
                Divider(Modifier.padding(vertical = MaterialTheme.spacing.small))
                Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
            }
        }
        SmallFloatingActionButton(
            onClick = onBackClick,
            modifier = Modifier.padding(MaterialTheme.spacing.normal)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }
}