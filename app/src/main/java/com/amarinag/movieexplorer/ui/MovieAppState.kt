package com.amarinag.movieexplorer.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.amarinag.feature.favorite.navigation.favoriteRoute
import com.amarinag.feature.favorite.navigation.navigateToFavorite
import com.amarinag.feature.nowplaying.navigation.navigateToNowPlaying
import com.amarinag.feature.nowplaying.navigation.nowPlayingRoute
import com.amarinag.movieexplorer.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMovieAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): MovieAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        MovieAppState(navController, coroutineScope, windowSizeClass)
    }
}

@Stable
class MovieAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    windowSizeClass: WindowSizeClass,

    ) {
    val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() = when (currentDestination?.route) {
            nowPlayingRoute -> TopLevelDestination.NOW_PLAYING
            favoriteRoute -> TopLevelDestination.FAVORITE
            else -> null
        }

    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.NOW_PLAYING -> navController.navigateToNowPlaying(topLevelNavOptions)
            TopLevelDestination.FAVORITE -> navController.navigateToFavorite(topLevelNavOptions)
        }
    }
}