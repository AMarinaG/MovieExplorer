package com.amarinag.feature.nowplaying

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
import com.amarinag.core.designsystem.theme.spacing
import com.amarinag.core.model.Movie

@Composable
internal fun NowPlayingRoute(
    modifier: Modifier = Modifier,
    viewModel: NowPlayingViewModel = hiltViewModel()
) {
    val state by viewModel.nowPlayingUiState.collectAsStateWithLifecycle()
    NowPlayingScreen(state)

}

@Composable
internal fun NowPlayingScreen(nowPlayingUiState: NowPlayingUiState) {
    when (nowPlayingUiState) {
        NowPlayingUiState.Loading -> LoadingState()
        is NowPlayingUiState.Error -> EmptyState()
        is NowPlayingUiState.Success -> NowPlayingGrid(nowPlayingUiState.movies)
    }
}

@Composable
private fun NowPlayingGrid(movies: List<Movie>) {
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
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.tiny,
                    vertical = MaterialTheme.spacing.tiny
                )
            ) {
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
internal fun EmptyState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.no_movies),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
internal fun LoadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.loading),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}