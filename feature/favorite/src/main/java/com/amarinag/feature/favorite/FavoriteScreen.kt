package com.amarinag.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.amarinag.core.designsystem.component.LoadingState
import com.amarinag.core.designsystem.theme.spacing
import com.amarinag.core.model.Movie

@Composable
internal fun FavoriteRoute(onMovieClick: (movieId: Int) -> Unit,viewModel: FavoriteViewModel = hiltViewModel()) {
    val favoriteState by viewModel.favoriteUiState.collectAsStateWithLifecycle()
    FavoriteScreen(
        favoriteUiState = favoriteState,
        onMovieClick = onMovieClick
    )
}

@Composable
internal fun FavoriteScreen(
    favoriteUiState: FavoriteUiState,
    onMovieClick: (movieId: Int) -> Unit
) {
    when (favoriteUiState) {
        FavoriteUiState.Loading -> LoadingState()
        is FavoriteUiState.Success -> if (favoriteUiState.movies.isNotEmpty()) {
            FavoriteGrid(
                movies = favoriteUiState.movies,
                onMovieClick = onMovieClick
            )

        } else EmptyState()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteGrid(
    movies: List<Movie>,
    onMovieClick: (movieId: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.tiny,
            vertical = MaterialTheme.spacing.tiny
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies, key = { it.id }) {
            Card(
                onClick = { onMovieClick(it.id) },
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.tiny,
                    vertical = MaterialTheme.spacing.tiny
                )
            ) {
//                Text(text = it.name)
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${it.posterPath}",
                    contentDescription = "null"
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.tiny),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.no_favorite_movies),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}