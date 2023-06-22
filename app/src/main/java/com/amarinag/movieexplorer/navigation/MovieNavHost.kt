package com.amarinag.movieexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.amarinag.feature.favorite.navigation.favoriteScreen
import com.amarinag.feature.nowplaying.navigation.nowPlayingRoute
import com.amarinag.feature.nowplaying.navigation.nowPlayingScreen
import com.amarinag.movieexplorer.ui.MovieAppState

@Composable
fun MovieNavHost(
    appState: MovieAppState,
    modifier: Modifier = Modifier,
    startDestination: String = nowPlayingRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        nowPlayingScreen()
        favoriteScreen()
    }
}