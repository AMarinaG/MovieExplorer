package com.amarinag.feature.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.amarinag.feature.favorite.FavoriteRoute

const val favoriteRoute = "favorite_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) {
    this.navigate(favoriteRoute, navOptions)
}

fun NavGraphBuilder.favoriteScreen(onMovieClick: (movieId: Int) -> Unit) {
    composable(favoriteRoute) {
        FavoriteRoute(onMovieClick)
    }
}