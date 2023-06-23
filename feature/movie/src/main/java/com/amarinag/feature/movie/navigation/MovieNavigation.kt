package com.amarinag.feature.movie.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.amarinag.feature.movie.MovieRoute

internal const val movieRoute = "movie_route"
internal const val movieIdArg = "movieId"

internal class MovieArgs(val movieId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(movieId = checkNotNull(savedStateHandle[movieIdArg]))
}

fun NavController.navigateToMovieDetail(movieId: Int) {
    this.navigate("$movieRoute/$movieId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.movieScreen(onBackClick: () -> Unit) {
    composable("$movieRoute/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) { type = NavType.IntType }
        )
    ) {
        MovieRoute(onBackClick = onBackClick)
    }
}