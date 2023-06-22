package com.amarinag.movieexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amarinag.feature.favorite.navigation.favoriteScreen
import com.amarinag.feature.nowplaying.navigation.nowPlayingRoute
import com.amarinag.feature.nowplaying.navigation.nowPlayingScreen

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = nowPlayingRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        nowPlayingScreen()
        favoriteScreen()
    }
}