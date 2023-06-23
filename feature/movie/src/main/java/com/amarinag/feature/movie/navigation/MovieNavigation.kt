package com.amarinag.feature.movie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.amarinag.feature.movie.MovieRoute

const val movieRoute = "movie_route"
internal const val movieIdArg = "movieId"

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